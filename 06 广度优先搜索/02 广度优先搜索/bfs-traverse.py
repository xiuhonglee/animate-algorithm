from collections import deque

class Graph:
    def __init__(self):
        """
        初始化图对象，使用一个空字典。
        字典中的每个键代表图中的一个顶点，
        对应的值是这个顶点的邻居列表。
        """
        self.graph_dict = {}

    def add_vertex(self, node):
        """
        如果顶点 "node" 不在字典中，向字典添加键 "node"，值为空列表。
        否则，不需要进行任何操作。
        """
        if node not in self.graph_dict:
            self.graph_dict[node] = []

    def add_edge(self, node1, node2):
        """
        要在图中添加一条边，
        需要在每个顶点的邻居列表中添加另一个顶点。
        """
        self.graph_dict[node1].append(node2)
        self.graph_dict[node2].append(node1)

    def bfs(self, start):
        """
        这个方法实现了广度优先搜索算法。
        它以 "start" 为起点进行搜索。
        返回一个列表，表示从 "start" 开始的广度优先搜索的顶点访问顺序。
        """
        visited = []
        queue = deque([start])

        while queue:
            vertex = queue.popleft()
            print(f"Queue after popping: {list(queue)}")
            if vertex not in visited:
                visited.append(vertex)
                neighbours = self.graph_dict[vertex]
                unvisited_neighbours = list(set(neighbours) - set(visited))
                queue.extend(unvisited_neighbours)
                print(f"Queue after extending: {list(queue)}") 

        return visited


# 测试 Graph 类
G = Graph()
G.add_vertex("悟空")
G.add_vertex("唐僧")
G.add_vertex("铁扇公主")
G.add_vertex("牛魔王")
G.add_vertex("猪八戒")
G.add_vertex("红孩儿")
G.add_vertex("小猪妖")

G.add_edge("悟空", "唐僧")
G.add_edge("悟空", "铁扇公主")
G.add_edge("悟空", "牛魔王")
G.add_edge("悟空", "猪八戒")

G.add_edge("唐僧", "悟空")
G.add_edge("唐僧", "猪八戒")

G.add_edge("铁扇公主", "悟空")
G.add_edge("铁扇公主", "牛魔王")
G.add_edge("铁扇公主", "红孩儿")

G.add_edge("牛魔王", "悟空")
G.add_edge("牛魔王", "铁扇公主")
G.add_edge("牛魔王", "红孩儿")

G.add_edge("猪八戒", "悟空")
G.add_edge("猪八戒", "唐僧")

G.add_edge("红孩儿", "铁扇公主")
G.add_edge("红孩儿", "牛魔王")
G.add_edge("红孩儿", "小猪妖")

G.add_edge("小猪妖", "红孩儿")

print(G.bfs("悟空"))  # 输出: ['悟空', '唐僧', '牛魔王', '猪八戒', '铁扇公主', '红孩儿', '小猪妖']

