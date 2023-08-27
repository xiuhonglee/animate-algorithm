class WeightedGraph:
    def __init__(self, vertices):
        # 初始化图，参数为顶点列表
        self.graph = {vertex: {} for vertex in vertices}

    def add_edge(self, src, dest, weight):
        # 添加边，参数为源节点，目标节点和权重
        # 因为是无向图，所以需要在源节点和目标节点的邻接列表中都添加对方节点和对应的权重
        if src in self.graph and dest in self.graph:
            self.graph[src][dest] = weight
            self.graph[dest][src] = weight
        else:
            raise ValueError("源节点或目标节点不在图中")

    def display(self):
        # 输出图的内容
        for vertex, edges in self.graph.items():
            print(vertex, "->", edges)

# 测试用例
vertices = ['A', 'B', 'C', 'D', 'E']

graph = WeightedGraph(vertices)
graph.add_edge('A', 'B', 10)
graph.add_edge('A', 'C', 20)
graph.add_edge('B', 'D', 30)
graph.add_edge('B', 'E', 40)
graph.add_edge('C', 'D', 50)
graph.add_edge('D', 'E', 60)
graph.display()
# 输出：
# A -> {'B': 10, 'C': 20}
# B -> {'A': 10, 'D': 30, 'E': 40}
# C -> {'A': 20, 'D': 50}
# D -> {'B': 30, 'C': 50, 'E': 60}
# E -> {'B': 40, 'D': 60}

