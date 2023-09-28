import numpy as np
from scipy.optimize import curve_fit

# Given data points
N_values = np.array([1e3, 2e3, 4e3, 8e3, 16e3, 32e3])
T_values = np.array([0.005, 0.012, 0.031, 0.098, 0.358, 1.389])

# Define the function for the model: T = aN^2 + bN + c
def model(N, a, b, c):
    return a * N**2 + b * N + c

# Use curve_fit to find the coefficients a, b, and c
params, covariance = curve_fit(model, N_values, T_values)
a, b, c = params
a, b, c

# RESULT
# (1.3182218917850921e-09, 1.0857478792859185e-06, 0.004204166976515905)
