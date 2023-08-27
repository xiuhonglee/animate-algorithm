def knapsack_optimized(W, weights, values):
    """
    使用一维DP数组解决0-1背包问题
    :param W: 背包的总容量
    :param weights: 每个物品的重量列表
    :param values: 每个物品的价值列表
    :return: 背包能装的最大价值
    """
    n = len(weights)  # 获取物品的数量
    dp = [0] * (W + 1)  # 初始化一维DP数组，大小为背包容量+1，所有位置初始化为0

    for i in range(n):
        # 从背包的容量开始递减，直到当前物品的重量。这是为了确保每个物品只被考虑一次
        for w in range(W, weights[i] - 1, -1):

            # 对于每个容量w，我们尝试放入物品i，并更新dp[w]的值。
            # dp[w]的新值是不放入物品i和放入物品i这两种选择中的最大值。
            dp[w] = max(dp[w], values[i] + dp[w - weights[i]])

    return dp[W]


weights = [2, 5, 7, 11, 13]
values = [5000, 8000, 7000, 9000, 11000]
W = 25

print(knapsack_optimized(W, weights, values))
