import numpy as np
import matplotlib.pyplot as plt
from sklearn.decomposition import PCA
from sklearn.neighbors import NearestNeighbors

# 模拟的数据：5部电影，5位用户
ratings = np.array([
    [5, 3, 0, 0, 2],
    [0, 4, 4, 3, 1],
    [2, 0, 5, 0, 0],
    [0, 0, 4, 5, 0],
    [3, 2, 0, 2, 3]
])

# 使用KNN找到与当前用户最相似的用户
# 这里我们设定为5个最近邻居来得到所有5位用户的距离
knn = NearestNeighbors(n_neighbors=5, metric='cosine', algorithm='brute')
knn.fit(ratings)

# 假设当前用户的评分是：[3, 0, 5, 0, 3]
current_user_rating = np.array([[3, 0, 5, 0, 3]])
distances, indices = knn.kneighbors(current_user_rating)

# 打印距离
for i, (index, distance) in enumerate(zip(indices[0], distances[0])):
    print(f"当前用户与用户{index}的距离为: {distance:.4f}")

# 使用PCA将数据降为2维以便进行可视化
pca = PCA(n_components=2)
transformed_ratings = pca.fit_transform(ratings)
transformed_current_user = pca.transform(current_user_rating)

# 使用matplotlib绘制散点图
plt.figure(figsize=(10, 6))

# 绘制所有用户
plt.scatter(transformed_ratings[:, 0], transformed_ratings[:, 1], label='Other Users', s=100)

# 绘制与当前用户最相似的用户
for index in indices[0]:
    plt.scatter(transformed_ratings[index, 0], transformed_ratings[index, 1], label=f'User {index}', s=100)

# 绘制当前用户
plt.scatter(transformed_current_user[:, 0], transformed_current_user[:, 1], marker='*', color='red', s=200, label='Current User')

plt.title('Visualization of User Similarities')
plt.xlabel('Principal Component 1')
plt.ylabel('Principal Component 2')
plt.legend()
plt.grid(True)
plt.show()
