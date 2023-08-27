# 定义物品列表，每个物品由重量和价值组成
items = [(10, 60), (20, 100), (30, 120)]
# 背包的容量
capacity = 50

# 计算每个物品的价值重量比，并按照价值重量比从高到低排序
items.sort(key=lambda x: x[1]/x[0], reverse=True)

# 初始化背包中物品的总价值
value = 0

# 依次选择价值重量比最高的物品
for item in items:
    if capacity >= item[0]:
        # 如果背包能装下物品，就把物品放入背包
        capacity -= item[0]
        value += item[1]
    else:
        # 如果背包装不下物品，就停止选择
        break

print("贪心策略得到的背包中物品的总价值为:", value)

