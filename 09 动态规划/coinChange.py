def coinChange(coins, amount):
    """
    使用动态规划解决硬币找零问题
    :param coins: 可用的硬币面额列表
    :param amount: 需要找的总金额
    :return: 所需的最少硬币数量
    """
    # dp[i]表示金额为i时所需的最少硬币数量
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0  # 初始条件，金额为0时所需硬币数量为0
    
    for coin in coins:
        for i in range(coin, amount + 1):
            dp[i] = min(dp[i], dp[i - coin] + 1)
    
    return dp[amount] if dp[amount] != float('inf') else -1

coins = [1, 2, 5]
amount = 11
coinChange(coins, amount)
