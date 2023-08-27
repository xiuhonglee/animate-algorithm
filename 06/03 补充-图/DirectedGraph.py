
## 有向图

class DirectedGraph:
    def __init__(self, vertices):
        # 初始化图，参数为顶点列表
        self.graph = {vertex: [] for vertex in vertices}

    def add_edge(self, src, dest):
        # 添加边，参数为源节点和目标节点
        # 注意因为是有向图，所以只需要在源节点的邻接表中添加目标节点
        if src in self.graph:
            self.graph[src].append(dest)
        else:
            raise ValueError("源节点不在图中")

    def display(self):
        # 输出图的内容
        for vertex, edges in self.graph.items():
            print(vertex, "->", edges)

# 测试用例
vertices = ['A', 'B', 'C', 'D', 'E', 'F', 'G']

graph = DirectedGraph(vertices)
graph.add_edge('A', 'B')
graph.add_edge('A', 'C')
graph.add_edge('B', 'D')
graph.add_edge('B', 'E')
graph.add_edge('C', 'F')
graph.add_edge('C', 'G')
graph.display()
# 输出：
# A -> ['B', 'C']
# B -> ['D', 'E']
# C -> ['F', 'G']
# D -> []
# E -> []
# F -> []
# G -> []

