def longestCommonSubsequence_with_path(X, Y):
    """
    计算两个序列X和Y的最长公共子序列，并返回子序列
    :param X: 序列X
    :param Y: 序列Y
    :return: 最长公共子序列的长度和子序列
    """
    m, n = len(X), len(Y)
    
    # 初始化dp表，记录每个子问题的最长公共子序列长度
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    # 逐个填充dp表的值
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            # 当前字符匹配
            if X[i - 1] == Y[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
            else:
                # 当前字符不匹配，取上方或左方的较大值
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

    # 回溯，构造最长公共子序列
    i, j = m, n
    lcs = []
    while i > 0 and j > 0:
        # 当前字符匹配
        if X[i - 1] == Y[j - 1]:
            lcs.append(X[i - 1])
            i -= 1
            j -= 1
        # 否则，移动到值较大的方向（上方或左方）
        elif dp[i - 1][j] > dp[i][j - 1]:
            i -= 1
        else:
            j -= 1
    lcs = lcs[::-1]  # 反转，得到正确的序列
    
    return dp[m][n], ''.join(lcs)

X = "ABCBDAB"
Y = "BDCAB"

longestCommonSubsequence_with_path(X, Y)
