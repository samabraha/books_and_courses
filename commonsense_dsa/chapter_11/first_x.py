def first_x(word, n = 0):
    x = -1
    if (word[n] == "x"):
        # print("here" + str(word.index("x")))
        print("N:", n)
        x = n
        return x
    first_x(word, n + 1)
    return x

print(first_x("Indexed"))