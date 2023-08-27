def fractional_knapsack(values, weights, capacity):
    """
    0/1 分数背包问题的贪心算法解决方案
    :param values: 物品的价值列表
    :param weights: 物品的重量列表
    :param capacity: 背包的容量
    :return: 背包中物品的最大价值
    """
    # 计算每个物品的单位重量价值
    unit_values = [(v / w, w, v) for v, w in zip(values, weights)]

    # 根据单位重量价值对物品进行排序（降序）
    unit_values.sort(key=lambda x: x[0], reverse=True)

    total_value = 0  # 总价值
    for uv, w, v in unit_values:
        # 如果背包可以完全装下当前物品
        if capacity >= w:
            capacity -= w
            total_value += v
        # 如果背包只能装下当前物品的一部分
        else:
            total_value += uv * capacity
            break

    return total_value

# 物品的价值
values = [60, 100, 120]
# 物品的重量
weights = [10, 20, 30]
# 背包的容量
capacity = 50

fractional_knapsack(values, weights, capacity)
