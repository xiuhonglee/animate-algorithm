def fibonacci_memo(n, memo={}):
    """
    备忘录法求第N个斐波那契数 (记忆化递归)
    """
    if n in memo:
        return memo[n]

    if n <= 1:
        return n
    else:
        result = fibonacci_memo(n-1, memo) + fibonacci_memo(n-2, memo)
        memo[n] = result # 存储问题结果
        return result


# 测试函数

# 栈溢出情况
# N = 1000

# 正常情况
N = 10
print(f"第 {N} 个斐波那契数（自上而下）：{fibonacci_memo(N)}")

