import matplotlib.pyplot as plt
import numpy as np
from scipy.special import factorial

n = np.linspace(0, 10, 400)  # 创建n的范围为0到10的400个点
y1 = 2 ** n                 # 2的n次方
y2 = factorial(n)           # n的阶乘

plt.plot(n, y1, label='2^n')  # 绘制2的n次方曲线
plt.plot(n, y2, label='n!')   # 绘制n的阶乘曲线

plt.title('Curves of 2^n and n!')  # 标题
plt.xlabel('n')  # x轴标签
plt.ylabel('Value')  # y轴标签

plt.yscale('log')  # 由于n!的增长速度非常快，因此可以考虑使用对数尺度

plt.legend()  # 显示图例
plt.grid(True)  # 显示网格

plt.show()  # 显示图像
