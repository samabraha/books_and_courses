def unique_paths(rows, columns, memo):
    if rows == 1 or columns == 1:
        return 1
    if not memo.get((rows, columns)):
        memo[rows, columns] = unique_paths(rows - 1, columns, memo) + unique_paths(rows, columns - 1, memo)
    
    return memo[rows, columns]
for n in range(5):
    print(f'Unique paths for square of {n}x{n} = {unique_paths(n, n, {})}')
