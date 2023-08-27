## 文件说明

### 1. 求解第 N 个斐波那契数

* [fibonacci.py](): 原始方法不存储重叠子问题结果
* [fibonacci_top_down.py](): 自上而下的递归方式(备忘录法)
* [fibonacci_bottom_up.py](): 自底而上的动态规划方式(表格方法)

### 2. 背包问题

* [knapsack.py](): 解决0-1背包问题的动态规划算法
* [knapsack_transposed.py](): 解决0-1背包问题的动态规划算法（颠倒DP表的横纵轴）
* [knapsack_1D.py](): 用一维数组构建DP表，优化空间

### 3. 背包等价问题

#### 3.1 背包问题等价问题 - 分割等和子集问题

* [canPartition.py](): DP表用二维数组
* [canPartition_1D.py](): DP表用一维数组

#### 3.2 背包问题相似问题 - 硬币找零问题

* [coinChange.py](): 只找出最小的硬币数量，不关系组合方式
* [coinChange_2D.py](): 使用二维DP表解决硬币找零问题，并记录选择的硬币 

#### 3.3 背包问题相似问题 - 分数背包问题

* [fractional_knapsack.py](): 贪心策略


### 4. 最长公共子序列

* [longestCommonSubsequence_with_path.py](): 包含回溯最长子序列



