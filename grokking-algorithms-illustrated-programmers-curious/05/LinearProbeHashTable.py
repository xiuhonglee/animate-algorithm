class LinearProbeHashTable:
    def __init__(self, size):
        """
        初始化散列表的大小和数组
        """
        self.size = size
        self.threshold = int(size * 0.7)  # 装填因子阈值
        self.count = 0  # 当前散列表中的元素个数
        self.hash_table = [None] * size

    def _hash(self, key):
        """
        散列函数，将键转换为散列索引
        """
        return key % self.size

    def _next_index(self, index):
        """
        获取下一个索引位置，使用线性探测法
        """
        return (index + 1) % self.size

    def _resize(self):
        """
        重新开辟地址空间，扩大散列表的大小并重新插入已有的键值对
        """
        self.size *= 2  # 扩大散列表的大小为原来的两倍
        self.threshold = int(self.size * 0.5)  # 更新装填因子阈值
        old_table = self.hash_table
        self.hash_table = [None] * self.size
        self.count = 0

        for item in old_table:
            if item is not None:
                key, value = item
                self.insert(key, value)  # 重新插入已有的键值对

    def insert(self, key, value):
        """
        向散列表中插入键值对，当装填因子达到阈值时重新开辟地址空间
        """
        if self.count >= self.threshold:
            self._resize()  # 装填因子超过阈值，重新开辟地址空间

        index = self._hash(key)  # 计算初始索引
        while self.hash_table[index] is not None:
            index = self._next_index(index)  # 冲突发生，获取下一个索引位置
        self.hash_table[index] = (key, value)  # 插入键值对
        self.count += 1

    def search(self, key):
        """
        在散列表中查找给定键的值
        """
        index = self._hash(key)  # 计算初始索引
        while self.hash_table[index] is not None:
            if self.hash_table[index][0] == key:
                return self.hash_table[index][1]  # 返回键对应的值
            index = self._next_index(index)  # 冲突发生，获取下一个索引位置
        return None  # 键不存在于散列表中

# 创建一个大小为10的散列表实例
hash_table = LinearProbeHashTable(10)

# 插入键值对
hash_table.insert(5, "Value 1")
hash_table.insert(15, "Value 2")
hash_table.insert(25, "Value 3")

# 查找键的值
print(hash_table.search(5))   # 输出: Value 1
print(hash_table.search(15))  # 输出: Value 2
print(hash_table.search(25))  # 输出: Value 3
print(hash_table.search(35))  # 输出: None (键不存在)


# 创建一个大小为3的散列表实例
hash_table2 = LinearProbeHashTable(3)

# 插入键值对，使装填因子超过阈值
hash_table2.insert(5, "Value 1")
hash_table2.insert(15, "Value 2")
hash_table2.insert(25, "Value 3")
hash_table2.insert(35, "Value 4")

# 查找键的值
print(hash_table2.search(5))   # 输出: Value 1
print(hash_table2.search(15))  # 输出: Value 2
print(hash_table2.search(25))  # 输出: Value 3
print(hash_table2.search(35))  # 输出: Value 4
