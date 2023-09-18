# 引入heapq库，用于实现优先队列
import heapq

# 定义迪杰斯特拉函数，输入为图、起始节点和目标节点
def dijkstra(graph, start, end):

    # 初始化一个字典来保存每个节点的最短距离和前一个节点，初始距离都设置为无穷大，前一个节点都设置为None
    shortest_distances = {node: (float('infinity'), None) for node in graph}

    # 起始节点的最短距离设为0，前一个节点为None
    shortest_distances[start] = (0, None)

    # 初始化一个优先级队列，将起始节点和其距离加入队列
    heap = [(0, start)]

    # 当队列不为空时进行循环
    while heap:

        # 使用heappop取出队列中最短距离的节点
        current_distance, current_node = heapq.heappop(heap)

        # 如果当前节点的距离已经被更新过（大于我们记录的最短距离），则跳过
        if current_distance > shortest_distances[current_node][0]:
            continue

        # 遍历当前节点的所有邻居节点
        for neighbor, distance in graph[current_node].items():

            # 计算经过当前节点到达邻居节点的距离
            new_distance = current_distance + distance

            # 如果经过当前节点到达邻居节点的距离比已知的邻居节点的最短距离还要短,
            # 就更新邻居节点的最短距离和前一节点
            if new_distance < shortest_distances[neighbor][0]:
                shortest_distances[neighbor] = (new_distance, current_node)

                # 将新的距离和邻居节点加入优先级队列
                heapq.heappush(heap, (new_distance, neighbor))

    # 到此为止，我们已经找到了所有节点的最短距离，下面根据前一节点信息找出最短路径
    path = []  # 初始化一个列表来保存路径
    current_node = end  # 从目标节点开始
    while current_node is not None:  # 如果当前节点不是None，说明还未到达起始节点
        path.append(current_node)  # 将当前节点添加到路径中
        next_node = shortest_distances[current_node][1]  # 获取当前节点的前一节点
        current_node = next_node  # 将前一节点设为当前节点
    path = path[::-1]  # 反转路径，使其从起始节点开始

    # 返回最短距离和最短路径
    return shortest_distances[end][0], path

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

# 调用函数，计算从节点0到节点4的最短路径
shortest_distance, shortest_path = dijkstra(graph, 0, 4)
print(f"节点0到节点4的最短距离为: {shortest_distance}")
print(f"节点0到节点4的最短路径为: {shortest_path}")

