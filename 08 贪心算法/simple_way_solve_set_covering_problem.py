from itertools import combinations

# 定义全集，表示需要覆盖的长江经济带所有省份
provinces_needed = set(["上海", "江苏", "浙江", "安徽", 
                        "江西", "湖南", "湖北", "重庆", 
                        "四川", "云南", "贵州"])

# 定义子集，表示每个广播台可以覆盖的省份
stations = {}

stations["一台"] = set(["上海", "江苏", "浙江"])
stations["二台"] = set(["安徽", "江西", "湖南"])
stations["三台"] = set(["湖北", "重庆", "四川"])
stations["四台"] = set(["云南", "贵州"])
stations["五台"] = set(["湖南", "湖北", "重庆"])

# 存储最终选择的广播台
final_stations = set()
# 存储最小广播台数量
min_length = float('inf')

# 遍历所有可能的组合
for i in range(1, len(stations) + 1):
    for subset in combinations(stations, i):
        provinces_covered = set([province for station in subset for province in stations[station]])
        # 如果这个组合可以覆盖所有的省份，且数量小于当前最小数量，则更新最小数量和最终选择的广播台
        if provinces_needed.issubset(provinces_covered) and len(subset) < min_length:
            min_length = len(subset)
            final_stations = subset

print(final_stations)

