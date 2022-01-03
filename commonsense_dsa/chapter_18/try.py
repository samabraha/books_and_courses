import sys
def go_in(level=0):
    if level > 10:
        print('Done')
        return level
    else:
        print(f'Level: {level}')
        level += 1
        go_in(level)

print(f'RL: {sys.getrecursionlimit()}')
go_in()