def lcs(X, Y):
    m, n = len(X), len(Y)  # 获取两个字符串的长度
    
    # 初始化一维DP数组，大小为字符串Y的长度+1，用于存储当前行的LCS长度
    dp = [0] * (n + 1)
    
    # 初始化一个方向数组，大小为m+1 x n+1
    # 用于记录我们如何到达每个DP值
    # 'diag'表示我们是从左上角的方向来的，'left'表示我们是从左边来的，'up'表示我们是从上面来的
    directions = [['' for _ in range(n + 1)] for _ in range(m + 1)]

    # 遍历字符串X
    for i in range(1, m + 1):
        prev = 0  # 保存前一列的dp[j]值，因为我们需要在下次迭代中使用它
        # 遍历字符串Y
        for j in range(1, n + 1):
            temp = dp[j]  # 保存当前的dp[j]值，以便于下一次迭代使用
            # 如果X的当前字符和Y的当前字符相同
            if X[i-1] == Y[j-1]:
                dp[j] = prev + 1  # 更新dp[j]值，这时我们是从左上角方向来的
                directions[i][j] = 'diag'
            else:
                # 如果X的当前字符和Y的当前字符不同
                # 我们需要从dp[j]（上方）和dp[j-1]（左方）中选择一个较大的值
                if dp[j] < dp[j-1]:
                    dp[j] = dp[j-1]  # 更新dp[j]值，这时我们是从左方来的
                    directions[i][j] = 'left'
                else:
                    # 如果dp[j]已经是较大的值，我们不需要更新它，但是我们是从上方来的
                    directions[i][j] = 'up'
            prev = temp  # 更新prev值为当前列的旧值

    # 使用方向数组回溯LCS
    i, j = m, n  # 从最后一个字符开始
    lcs_str = []  # 用于存储LCS的字符
    while i > 0 and j > 0:
        # 如果我们是从左上角方向来的，那么X[i-1]和Y[j-1]是LCS的一部分
        if directions[i][j] == 'diag':
            lcs_str.append(X[i-1])
            i -= 1
            j -= 1
        # 如果我们是从左方来的，仅仅移动到Y的前一个字符
        elif directions[i][j] == 'left':
            j -= 1
        # 如果我们是从上方来的，仅仅移动到X的前一个字符
        else:
            i -= 1

    # 因为我们是从后向前回溯的，所以需要反转LCS字符串来得到正确的顺序
    lcs_str = lcs_str[::-1]
    return dp[n], ''.join(lcs_str)  # 返回LCS的长度和LCS字符串

# 测试
X = "ABCBDAB"
Y = "BDCAB"
length, sequence = lcs(X, Y)
print(length)  # 输出4
print(sequence)  # 输出"BCAB"
