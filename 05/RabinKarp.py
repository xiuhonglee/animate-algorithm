class RabinKarp:
    def __init__(self, pattern):
        """
        初始化 RabinKarp 类。

        Args:
            pattern (str): 模式字符串。

        """
        self.pattern = pattern
        self.pattern_hash = self.calculate_hash(pattern)

    def calculate_hash(self, text):
        """
        计算字符串的哈希值。

        Args:
            text (str): 输入的字符串。

        Returns:
            int: 字符串的哈希值。

        """
        prime = 31  # 选择一个适当的质数作为散列计算的基数
        hash_value = 0
        for char in text:
            hash_value = (hash_value * prime + ord(char)) % 1000000007  # 使用简单的散列计算方式
        return hash_value

    def search(self, text):
        """
        在文本字符串中搜索模式字符串。

        Args:
            text (str): 输入的文本字符串。

        Returns:
            int: 如果模式字符串存在于文本字符串中，返回匹配的起始索引；否则返回 -1。

        """
        n = len(self.pattern)
        m = len(text)
        pattern_hash = self.pattern_hash
        text_hash = self.calculate_hash(text[:n])

        for i in range(m - n + 1):
            if pattern_hash == text_hash and self.pattern == text[i:i+n]:
                return i

            if i < m - n:
                # 通过滑动窗口更新文本子串的哈希值
                text_hash = (text_hash - ord(text[i]) * pow(31, n-1)) % 1000000007
                text_hash = (text_hash * 31 + ord(text[i+n])) % 1000000007
                text_hash = (text_hash + 1000000007) % 1000000007

        return -1


# 使用示例
text = "Hello, world! This is a test string."
pattern = "test"
rk = RabinKarp(pattern)
result = rk.search(text)

if result != -1:
    print(f"Pattern found at index {result}")
else:
    print("Pattern not found")
