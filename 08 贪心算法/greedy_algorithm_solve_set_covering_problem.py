# 定义全集，表示需要覆盖的所有省份
provinces_needed = set((["上海", "江苏", "浙江", "安徽", 
                         "江西", "湖南", "湖北", "重庆", 
                         "四川", "云南", "贵州"]))

# 定义子集，表示每个广播台可以覆盖的省份
stations = {}
stations["一台"] = set(["上海", "江苏", "浙江"])
stations["二台"] = set(["安徽", "江西", "湖南"])
stations["三台"] = set(["湖北", "重庆", "四川"])
stations["四台"] = set(["云南", "贵州"])
stations["五台"] = set(["湖南", "湖北", "重庆"])

# 存储最终选择的广播台
final_stations = set()

# 使用贪心算法选择覆盖省份
while provinces_needed:
    # 初始化最佳广播台和覆盖省份集合
    best_station = None
    states_covered = set()

    # 遍历所有广播台，选择覆盖未覆盖省份最多的广播台
    for station, provinces in stations.items():
        covered = provinces_needed & provinces
        if len(covered) > len(states_covered):
            best_station = station
            states_covered = covered

    # 打印每次选择的广播台和覆盖的省份
    print("选择的广播台：", best_station)
    print("覆盖的省份：", states_covered)
    print("==============")

    # 将已覆盖的省份从未覆盖省份集合中移除
    provinces_needed -= states_covered
    # 将选中的广播台加入最终结果集
    final_stations.add(best_station)

print("最终选择的广播台集合：", final_stations)

