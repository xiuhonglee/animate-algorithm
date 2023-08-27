# 只计算最短路径，不保存最短路径


import heapq

def dijkstra(graph, start):
    # 创建一个字典，用于储存每个节点的最短距离，初始值设为无穷大
    shortest_distances = {node: float('infinity') for node in graph}
    # 将起点的最短距离设为0
    shortest_distances[start] = 0
    # 创建一个空的优先级队列，并将起点添加进去
    heap = [(0, start)]

    while heap:
        # 获取当前距离最短的节点
        current_distance, current_node = heapq.heappop(heap)

        # 如果当前节点的距离已经更新过，就跳过
        if current_distance > shortest_distances[current_node]:
            continue

        # 遍历当前节点的邻居节点
        for neighbor, distance in graph[current_node].items():
            # 计算经过当前节点到达邻居节点的距离
            new_distance = current_distance + distance

            # 如果经过当前节点到达邻居节点的距离比之前计算的距离还要短，就更新最短距离
            if new_distance < shortest_distances[neighbor]:
                shortest_distances[neighbor] = new_distance
                heapq.heappush(heap, (new_distance, neighbor))

    return shortest_distances

# 定义图
graph = {
    0: {1: 30, 7: 52},
    1: {0: 30, 7: 35, 2: 60},
    2: {1: 60, 8: 10, 3: 74, 5: 130},
    3: {2: 74, 5: 55, 4: 108},
    4: {3: 108, 5: 50},
    5: {2: 130, 3: 55, 4: 50, 6: 110},
    6: {5: 110, 7: 57, 8: 10},
    7: {0: 52, 1: 35, 8: 48, 6: 57},
    8: {2: 10, 6: 10, 7: 48}
}

# 调用函数，计算从节点0到其他所有节点的最短路径
shortest_distances = dijkstra(graph, 0)
print(shortest_distances)

