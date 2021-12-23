def fib(n):
    if n == 0 or n == 1:
        return n
    
    return fib(n - 2) + fib(n - 1)

# tests = [0, 1, 4, 5, 6, 23, 14]
for n in range(35):
    print(f'{n}th fib is {fib(n)}')
