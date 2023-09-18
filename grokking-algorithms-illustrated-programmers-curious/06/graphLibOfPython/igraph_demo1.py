
from igraph import Graph, summary

# 创建一个含有10000个顶点，50000条边的随机图
g = Graph.Erdos_Renyi(n=10000, m=50000)

# 打印图的基本信息
summary(g)

# 打印图的直径
print("Diameter of the graph: ", g.diameter())

# 计算并打印图的聚类系数
print("Clustering coefficient: ", g.transitivity_undirected())
