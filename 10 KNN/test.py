import numpy as np
from sklearn.neighbors import NearestNeighbors


knn = NearestNeighbors(n_neighbors=3, metric='cosine', algorithm='brute')

data = [[1, 2], [3, 4], [5, 6], [7, 8], [9, 10]]

# 使用数据拟合对象
knn.fit(data)

# 查询数据点 [4,5] 的最近邻
distances, indices = knn.kneighbors([[4, 5]])

print("Distances:", distances)
print("Indices:", indices)