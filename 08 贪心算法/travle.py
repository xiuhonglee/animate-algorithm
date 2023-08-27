import numpy as np

# 城市之间的距离矩阵
# 这里为了简单，我们假设距离矩阵为随机生成的，实际应用中，距离应由实际的地理信息计算得出
distance_matrix = np.random.rand(5, 5)

# 城市列表
cities = ["上海", "南京", "武汉", "重庆", "成都"]

# 选择一个起始城市
current_city = 0

# 存储旅行路线
route = [cities[current_city]]

# 待访问城市集合
to_visit = set(range(1, 5))

while to_visit:
    # 在待访问城市中选择距离当前城市最近的城市
    next_city = min(to_visit, key=lambda x: distance_matrix[current_city][x])
    to_visit.remove(next_city)
    
    # 更新当前城市，并将其添加到旅行路线中
    current_city = next_city
    route.append(cities[current_city])

# 最后返回起始城市
route.append(cities[0])

print("旅行路线：", route)
