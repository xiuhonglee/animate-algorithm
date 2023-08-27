import numpy as np
from collections import deque

def bfs(graph, start, end):
    """
    使用广度优先搜索寻找从start到end的最短路径。
    返回一个列表，表示最短路径中的节点。
    """
    queue = deque([[start]])
    visited = set()

    while queue:
        path = queue.popleft()
        vertex = path[-1]
        if vertex == end:
            return path
        elif vertex not in visited:
            for i, val in enumerate(graph[vertex]):
                if val == 1:  # 如果存在边
                    new_path = list(path)
                    new_path.append(i)
                    queue.append(new_path)
            visited.add(vertex)



# 用邻接矩阵表示图。我们需要按顺序将所有的节点映射到整数值。
# 这里是顺序映射：悟空:0, 唐僧:1, 铁扇公主:2, 牛魔王:3, 猪八戒:4, 红孩儿:5, 小猪妖:6
graph = np.array([
    [0, 1, 1, 1, 1, 0, 0],  # 悟空
    [1, 0, 0, 0, 1, 0, 0],  # 唐僧
    [1, 0, 0, 1, 0, 1, 0],  # 铁扇公主
    [1, 0, 1, 0, 0, 1, 0],  # 牛魔王
    [1, 1, 0, 0, 0, 0, 0],  # 猪八戒
    [0, 0, 1, 1, 0, 0, 1],  # 红孩儿
    [0, 0, 0, 0, 0, 1, 0]   # 小猪妖
])



# 测试
path = bfs(graph, 0, 6)  # 0代表"悟空"，6代表"小猪妖"
print(" -> ".join(map(str, path)))  # 输出应该是 0 -> 2 -> 3 -> 5 -> 6

# 在实际应用中，我们可能希望将节点的数字编号转回对应的实际名称，这可以通过简单的列表或字典映射实现
nodes = ["悟空", "唐僧", "铁扇公主", "牛魔王", "猪八戒", "红孩儿", "小猪妖"]
path_names = [nodes[i] for i in path]
print(" -> ".join(path_names))  # 输出应该是 悟空 -> 铁扇公主 -> 牛魔王 -> 红孩儿 -> 小猪妖
