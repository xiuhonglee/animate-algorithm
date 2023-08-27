from collections import deque

def bfs(graph, start, end):
    """
    使用广度优先搜索寻找从start到end的最短路径。
    返回一个列表，表示最短路径中的节点。
    """
    queue = deque([[start]])

    # 创建一个空集合，存储已经访问过的节点
    visited = set()

    while queue:
        path = queue.popleft()
        # 获取路径上最后一个节点
        vertex = path[-1]

        # 如果该节点是目标节点，返回当前路径（即为最短路径）
        if vertex == end:
            return path
        
        elif vertex not in visited:
            # 遍历该节点的所有邻居节点
            for neighbour in graph[vertex]:

                # 对于每一个邻居节点，都将其加到当前路径的尾部，形成一个新的路径
                new_path = list(path)
                new_path.append(neighbour)

                # 将新的路径添加到队列的右侧
                queue.append(new_path)
                
            # 将当前节点标记为已访问
            visited.add(vertex)

# 用邻接列表表示图
graph = {
    "悟空": ["唐僧", "铁扇公主", "牛魔王", "猪八戒"],
    "唐僧": ["悟空", "猪八戒"],
    "铁扇公主": ["悟空", "牛魔王", "红孩儿"],
    "牛魔王": ["悟空", "铁扇公主", "红孩儿"],
    "猪八戒": ["悟空", "唐僧"],
    "红孩儿": ["铁扇公主", "牛魔王", "小猪妖"],
    "小猪妖": ["红孩儿"],
}

# 测试
path = bfs(graph, "悟空", "小猪妖")
# 注意：最短路径可能不止一条，打印结果只是其中一条
print(" -> ".join(path))  # 输出：悟空 -> 铁扇公主 -> 红孩儿 -> 小猪妖


path2 = bfs(graph, "悟空", "悟空")
print(" -> ".join(path2))


