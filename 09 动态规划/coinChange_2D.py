def coinChange_2D(coins, amount):
    """
    使用二维DP表解决硬币找零问题，并记录选择的硬币
    :param coins: 可用的硬币面额列表
    :param amount: 需要找的总金额
    :return: 所需的最少硬币数量和选择的硬币列表
    """
    n = len(coins)
    # 初始化DP表
    dp = [[float('inf') for _ in range(amount + 1)] for _ in range(n + 1)]
    for i in range(n + 1):
        dp[i][0] = 0  # 金额为0时所需硬币数量为0

    # 更新DP表
    for i in range(1, n + 1):
        for j in range(1, amount + 1):
            # 不选择第i个硬币
            dp[i][j] = dp[i-1][j]
            # 选择第i个硬币，条件是金额不小于硬币面额
            if j >= coins[i-1]:
                dp[i][j] = min(dp[i][j], dp[i][j-coins[i-1]] + 1)

    # 回溯找到选择的硬币
    coin_selection = []
    j = amount
    for i in range(n, 0, -1):
        while j >= coins[i-1] and dp[i][j] == dp[i][j-coins[i-1]] + 1:
            coin_selection.append(coins[i-1])
            j -= coins[i-1]

    if dp[n][amount] == float('inf'):
        return -1, []
    return dp[n][amount], coin_selection

coins = [3, 5, 7, 11]
amount = 28

coinChange_2D(coins, amount)
