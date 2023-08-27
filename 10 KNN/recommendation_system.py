import numpy as np
from sklearn.neighbors import NearestNeighbors

# 模拟的数据：5部电影，5位用户
# 每行代表一个用户对5部电影的评分（评分范围1-5，0表示未评分）
ratings = np.array([
    [5, 3, 0, 0, 2],
    [0, 4, 4, 3, 1],
    [2, 0, 5, 0, 0],
    [0, 0, 4, 5, 0],
    [3, 2, 0, 2, 3]
])

# 使用KNN找到与当前用户最相似的用户
# 这里选择3个最近邻居
knn = NearestNeighbors(n_neighbors=3, metric='cosine', algorithm='brute')
knn.fit(ratings)

# 假设当前用户的评分是：[3, 0, 5, 0, 3]
current_user_rating = np.array([[3, 0, 5, 0, 3]])
distances, indices = knn.kneighbors(current_user_rating)

# 基于最近邻用户的评分来推荐电影
# 我们推荐评分最高且当前用户未评分的电影
mean_rating = np.mean(ratings[indices], axis=1)
recommended_movie_index = np.argmax(mean_rating)

print(f"推荐电影索引为: {recommended_movie_index}")

# 注意：此示例为简化版，实际应用中需考虑更多因素和优化。


