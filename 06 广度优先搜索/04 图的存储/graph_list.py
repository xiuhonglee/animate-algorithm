class Vertex:
    """图中的顶点"""
    def __init__(self, key):
        """初始化顶点的键和相邻顶点集"""
        self.id = key
        self.connected_to = {}

    def add_neighbor(self, neighbor, weight=0):
        """添加一个相邻的顶点以及它的权重"""
        self.connected_to[neighbor] = weight

    def get_connections(self):
        """返回所有相邻顶点的键"""
        return self.connected_to.keys()

    def get_id(self):
        """返回顶点的键"""
        return self.id

    def get_weight(self, neighbor):
        """返回一个相邻顶点的权重"""
        return self.connected_to[neighbor]


class Graph:
    """图类，使用邻接表存储"""
    def __init__(self):
        """初始化图的顶点集"""
        self.vert_list = {}
        self.num_vertices = 0

    def add_vertex(self, key):
        """添加一个顶点到图中"""
        self.num_vertices += 1
        new_vertex = Vertex(key)
        self.vert_list[key] = new_vertex
        return new_vertex

    def get_vertex(self, key):
        """根据键获取一个顶点"""
        return self.vert_list.get(key)

    def add_edge(self, frm, to, cost=0):
        """添加一条边到图中"""
        if frm not in self.vert_list:
            self.add_vertex(frm)
        if to not in self.vert_list:
            self.add_vertex(to)
        self.vert_list[frm].add_neighbor(self.vert_list[to], cost)

    def get_vertices(self):
        """返回图中所有顶点的键"""
        return self.vert_list.keys()

    def __iter__(self):
        """迭代返回图中所有顶点"""
        return iter(self.vert_list.values())



## 测试
g = Graph()
for i in range(6):
    g.add_vertex(i)

g.add_edge(0, 1, 5)
g.add_edge(0, 5, 2)
g.add_edge(1, 2, 4)
g.add_edge(2, 3, 9)
g.add_edge(3, 4, 7)
g.add_edge(3, 5, 3)
g.add_edge(4, 0, 1)
g.add_edge(5, 4, 8)
g.add_edge(5, 2, 1)

for v in g:
    for w in v.get_connections():
        print("( %s , %s )" % (v.get_id(), w.get_id()))
