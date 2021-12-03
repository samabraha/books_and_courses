def greatest_product(array):
    greatest_product_so_far = array[0] * array[1]

    for i, iVal in enumerate(array):
        for j, jVal in enumerate(array):
            if i != j and iVal * jVal > greatest_product_so_far:
                # print(f'{iVal} x {jVal} = {iVal * jVal}')
                greatest_product_so_far = iVal * jVal
    return greatest_product_so_far


my_list = [5, 3, 11, 9, 6, 8]
print(my_list)
print(greatest_product(my_list))