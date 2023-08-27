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

    def show_edges(self):
        """
        此方法返回一个元组列表，每个元组代表图中的一条边。
        元组的两个元素是这条边连接的两个顶点。
        """
        edges = []
        for node in self.graph_dict:
            for neighbour in self.graph_dict[node]:
                if {neighbour, node} not in edges:
                    edges.append({node, neighbour})
        return edges


# 初始化图

G = Graph()

# 添加节点
G.add_vertex("孙悟空")
G.add_vertex("猪八戒")
G.add_vertex("唐僧")
G.add_vertex("牛魔王")
G.add_vertex("铁扇公主")
G.add_vertex("红孩儿")
G.add_vertex("小妖怪")

# 添加边
G.add_edge("孙悟空", "猪八戒")
G.add_edge("孙悟空", "唐僧")
G.add_edge("孙悟空", "牛魔王")
G.add_edge("孙悟空", "铁扇公主")
G.add_edge("铁扇公主", "红孩儿")
G.add_edge("牛魔王", "红孩儿")
G.add_edge("牛魔王", "铁扇公主")
G.add_edge("猪八戒", "唐僧")


print(G.graph_dict)  # 输出: {1: [2], 2: [1]}
print(G.show_edges())  # 输出: [{1, 2}]

