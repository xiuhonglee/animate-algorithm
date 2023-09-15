def josephus(n, k):
    result = 0  # 当只有一个人时，最后一个存活的人的索引为 0
    for i in range(2, n + 1):
        result = (result + k) % i
    return result + 1  # 转换为从 1 开始的编号

# n = 41（人数），k = 3（每数到第 3 个人就淘汰）
print("最后存活下来的人的编号是:", josephus(41, 3))