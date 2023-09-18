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

# 计算平均评分，但要忽略评分为0的部分
neighbors_ratings = ratings[indices][0]  # 提取邻居的评分
sum_ratings = np.sum(neighbors_ratings, axis=0)
count_nonzero = np.count_nonzero(neighbors_ratings, axis=0)

# 避免除以0
mean_ratings = np.divide(sum_ratings, count_nonzero, out=np.zeros_like(sum_ratings, dtype=float), where=count_nonzero != 0)

# 打印出2号和4号电影（索引1和3）的预测评分
print(f"预测的2号电影评分为: {mean_ratings[1]}")
print(f"预测的4号电影评分为: {mean_ratings[3]}")
