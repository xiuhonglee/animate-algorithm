import matplotlib.pyplot as plt
import numpy as np

# 定义模型参数
a = 1.318221890610448E-9
b = 1.085747925662513E-6
c = 0.004204166666666693

# 生成N的值。这里我们以1000为步长生成了一系列的N
N = np.arange(1000, 35000, 1000)

# 根据模型计算y的值
y = a * N**2 + b * N + c

# 绘制图形
plt.figure(figsize=(10,6))
plt.plot(N, y, '-o', label='y = {:.2e} * N^2 + {:.2e} * N + {:.2e}'.format(a, b, c))
plt.xlabel('N')
plt.ylabel('y')
plt.title('Plot of the Mathematical Model')
plt.legend()
plt.grid(True)
plt.show()
