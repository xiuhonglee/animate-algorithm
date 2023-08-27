# 定义自下而上（表格方法）的函数
def fibonacci_bottom_up(N):
    # 用 0 初始化 DP 数组，并设置 F(0) 和 F(1) 的基本情况
    dp = [0] * (N + 1)
    dp[1] = 1

    # 从 F(2) 开始，自下而上构建解决方案
    for i in range(2, N + 1):
        dp[i] = dp[i - 1] + dp[i - 2]

    # 返回第 N 个斐波那契数
    return dp[N]

# 测试函数
N = 1000
print(f"第 {N} 个斐波那契数（自下而上）：{fibonacci_bottom_up(N)}")
