def lcs(X, Y):
    """
    使用一维DP数组解决LCS问题
    :param X: 字符串X
    :param Y: 字符串Y
    :return: LCS的长度
    """
    m, n = len(X), len(Y)
    
    # 初始化一维DP数组，大小为字符串Y的长度+1
    dp = [0] * (n + 1)
    
    for i in range(1, m + 1):
        prev = 0  # 保存dp[j-1]的旧值，对应于二维DP数组中的dp[i-1][j-1]
        for j in range(1, n + 1):
            temp = dp[j]  # 保存当前dp[j]的值，因为我们需要在下次迭代中使用它
            if X[i-1] == Y[j-1]:
                dp[j] = prev + 1
            else:
                dp[j] = max(dp[j], dp[j-1])
            prev = temp  # 更新prev的值为dp[j]的旧值

    return dp[n]

# 测试
X = "ABCBDAB"
Y = "BDCAB"
print(lcs(X, Y))  # 输出4，最长公共子序列为"BCAB"
