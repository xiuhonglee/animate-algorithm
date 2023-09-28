import numpy as np
import matplotlib.pyplot as plt
import math

# 定义模型函数
def model(N, a, b, c):
    return a * N**2 * np.log(N) + b * N * np.log(N) + c

# 定义参数
a = 1.961900952491261e-09
b = 2.4380254620244043e-06
c = -0.034854130547573775

# 生成x数据
x_data = np.linspace(1000, 32000, 400)
# 计算y数据
y_data = model(x_data, a, b, c)

# 绘图
plt.plot(x_data, y_data, label='Model')
plt.scatter([1000, 2000, 4000, 8000, 16000, 32000], [0.015, 0.064, 0.296, 1.238, 5.228, 21.611], color='red', label='Data')
plt.xlabel('N')
plt.ylabel('Time (s)')
plt.legend()
plt.show()
