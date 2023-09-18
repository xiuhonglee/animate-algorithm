import networkx as nx
import matplotlib.pyplot as plt

# 创建一个含有10个节点的完全图
G = nx.complete_graph(10)

# 绘制图
nx.draw(G, with_labels=True)

# 显示图形
plt.show()
