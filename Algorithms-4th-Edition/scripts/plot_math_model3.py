import numpy as np
import matplotlib.pyplot as plt
import math

# 定义模型函数
def model1(N, a, b, c):
    return a * N**2 + b * N + c

def model2(N, a, b, c):
    return a * N**2 * np.log(N) + b * N * np.log(N) + c

# 定义参数
a1 = 1.318221890610448E-9
b1 = 1.085747925662513E-6
c1 = 0.004204166666666693

a2 = 1.961900952491261e-09
b2 = 2.4380254620244043e-06
c2 = -0.034854130547573775

# 生成x数据
x_data = np.linspace(1000, 32000, 400)
# 计算y数据
y_data1 = model1(x_data, a1, b1, c1)
y_data2 = model2(x_data, a2, b2, c2)

# 绘图
plt.plot(x_data, y_data1, label='Model 1')
plt.scatter([1000, 2000, 4000, 8000, 16000, 32000], [0.005, 0.012, 0.031, 0.098, 0.358, 1.389], color='red', marker='o', label='Data 1')

plt.plot(x_data, y_data2, label='Model 2')
plt.scatter([1000, 2000, 4000, 8000, 16000, 32000], [0.015, 0.064, 0.296, 1.238, 5.228, 21.611], color='blue', marker='x', label='Data 2')

plt.xlabel('N')
plt.ylabel('Time (s)')
plt.legend()
plt.show()
