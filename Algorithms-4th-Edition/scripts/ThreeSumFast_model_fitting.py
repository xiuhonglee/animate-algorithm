# ThreeSumFast 模型确定

from scipy.optimize import curve_fit
import numpy as np

# Define the model function
def model(N, a, b, c, d):
    return a * N**2 * np.log(N) + b * N * np.log(N) + c * N + d

# Given data points
N_values_2 = np.array([1000, 2000, 4000, 8000, 16000, 32000])

# 需要换成你的测试结果
T_values_2 = np.array([0.015, 0.064, 0.296, 1.238, 5.118, 21.611])

# Fit the model to the data and find the optimal parameters
params_opt_2, params_cov_2 = curve_fit(model, N_values_2, T_values_2, maxfev=5000)

# Extract the parameters
a_2_new, b_2_new, c_2_new, d_2_new = params_opt_2

# Print the parameters
print("a =", a_2_new)
print("b =", b_2_new)
print("c =", c_2_new)
print("d =", d_2_new)

# RESULT
# a = 2.016018884377828e-09
# b = -3.5589716787117615e-06
# c = 4.378173843180173e-05
# d = -0.024066987491874164
