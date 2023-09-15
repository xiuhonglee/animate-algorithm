import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
import numpy as np

# 定义向量A和B
A = np.array([2, 1, 8])
B = np.array([6, 10, 1])

# 创建一个3D绘图对象
fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

# 绘制原点
ax.scatter(0, 0, 0, color="k", s=100, label="Origin")

# 绘制向量A
ax.quiver(0, 0, 0, A[0], A[1], A[2], color='b', label='Vector A')

# 绘制向量B
ax.quiver(0, 0, 0, B[0], B[1], B[2], color='r', label='Vector B')

# 设置坐标轴
ax.set_xlim([0, 3])
ax.set_ylim([0, 3])
ax.set_zlim([0, 3])
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Z')

# 添加图例
ax.legend()

# 展示图形
plt.show()
