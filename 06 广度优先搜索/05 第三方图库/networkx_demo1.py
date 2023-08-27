import networkx as nx
import matplotlib.pyplot as plt

# 创建一个空的无向图
G = nx.Graph()

# 添加节点，从1到6
G.add_nodes_from(range(1, 7))

# 添加边
G.add_edge(1, 2)
G.add_edge(1, 3)
G.add_edge(2, 4)
G.add_edge(2, 5)
G.add_edge(3, 6)

# 绘制图
nx.draw(G, with_labels=True)

# 显示图形
plt.show()
