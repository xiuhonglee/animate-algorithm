def fibonacci_recursive(N):
    """
    使用递归的方法求解第N个斐波那契数
    :param N: 指定的斐波那契数的位置
    :return: 第N个斐波那契数
    """
    if N <= 0:
        return 0
    elif N == 1:
        return 1
    else:
        return fibonacci_recursive(N - 1) + fibonacci_recursive(N - 2)

# 求解第10个斐波那契数
fibonacci_recursive(10)
