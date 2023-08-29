# 注意：这里只是采用尾递归优化形式!
# Python 并没有优化尾递归，这意味着即使你写出了尾递归形式的函数，
# Python解释器也不会自动将其转换为循环以节省栈空间。
# 这是因为Python的设计哲学和实现方式不将尾递归优化视为优先事项

def fibonacci_tail(n, a=0, b=1):
    # 当 n 等于 0 时，返回 a（此时的斐波那契数列第0项）
    if n == 0:
        return a
    # 当 n 等于 1 时，返回 b（此时的斐波那契数列第1项）
    elif n == 1:
        return b
    # 对于其他情况，执行尾递归
    else:
        # 函数调用自身，n 减少 1，并更新 a 和 b 的值（a = b, b = a + b）
        return fibonacci_tail(n-1, b, a+b)

# 计算斐波那契数列的第10项
result = fibonacci_tail(10)
print(result)
