def knapsack(W, weights, values, n):
    """
    解决0-1背包问题的动态规划函数
    :param W: 背包的总容量
    :param weights: 每个物品的重量列表
    :param values: 每个物品的价值列表
    :param n: 物品的数量
    :return: 背包能装的最大价值
    """
    # 构造DP表，行数为物品数量+1，列数为背包容量+1
    K = [[0 for w in range(W + 1)] for i in range(n + 1)]
    # print(K)

    # 逐行填充DP表
    for i in range(n + 1):
        for w in range(W + 1):
            if i == 0 or w == 0:
                K[i][w] = 0  # 填充基础情况
            elif weights[i-1] <= w:
                # 当前物品可以装入背包时，考虑装入和不装入两种情况
                K[i][w] = max(values[i-1] + K[i-1][w-weights[i-1]],  K[i-1][w])
            else:
                # 当前物品不能装入背包时，只能选择不装入
                K[i][w] = K[i-1][w]

    # DP表的最后一个元素即为问题的最优解
    return K[n][W]

# 物品的重量
weights = [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
# 物品的价值
values = [60, 100, 120, 130, 150, 200, 220, 250, 300, 350]
# 物品的数量
n = len(values)
# 背包的容量
W = 250

print(knapsack(W, weights, values, n))  # 输出背包能装的最大价值

