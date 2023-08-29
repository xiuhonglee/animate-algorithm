# 采用递归的方式求第N个斐波那契数
def fibonacci(n):
    if n <= 1:
        return n
    else:
        return fibonacci(n-1) + fibonacci(n-2)

# 测试代码
n = 10
print("斐波那契数列的第", n, "个数是：", fibonacci(n))
