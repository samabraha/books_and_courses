def fib(n, flist):
    if n == 0 or n == 1:
        return n    
    
    if not flist.get(n):
        flist[n] = fib(n - 2, flist) + fib(n - 1, flist)
    return flist[n]
    # return fib(n - 2) + fib(n - 1)

for n in range(100):
    print(f'{n}th fib is {fib(n, {})}')
    # print(f'{n}th fib is {fib(n)}')

