import numpy as np  # 导入NumPy库，用于数组操作
from sklearn.linear_model import LinearRegression  # 导入sklearn的线性回归模型
import math  # 导入数学库

# 给定数据点 (可以换成自己的运行结果)
# 数据第一列是数据规模（n），第二列是运行时间（T）
data = np.array([
    [1, 0.175],
    [2, 1.213],
    [4, 9.41],
    [8, 74.941]
])

# 提取数据规模（n）和运行时间（T）
n = data[:, 0]
T = data[:, 1]

# 对数变换
# 为了将幂指模型线性化，我们对n和T取对数
log_n = np.log(n)
log_T = np.log(T)

# 调整数据形状以适应sklearn
# sklearn要求输入为二维数组
log_n = log_n.reshape(-1, 1)
log_T = log_T.reshape(-1, 1)

# 执行线性回归
# 使用对数变换后的数据进行线性回归，以估算模型参数
model = LinearRegression()
model.fit(log_n, log_T)

# 提取斜率（k）和截距（log(a)）
# 斜率和截距用于构建幂指模型
k = model.coef_[0][0]
log_a = model.intercept_[0]

# 计算a的值
# 通过对截距取指数得到a的值
a = math.exp(log_a)

# 输出k和a
print(k, a)

# RESULT
# 2.9182384662267324 0.16830073785100377