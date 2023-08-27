def fibonacci_tail(n, a=0, b=1):
    if n == 0:
        return a
    elif n == 1:
        return b
    else:
        return fibonacci_tail(n-1, b, a+b)

result = fibonacci_tail(10)
print(result)

