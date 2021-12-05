def insertSort(my_list):
    for index in range(1, len(my_list)):
        temp = my_list[index]
        pos = index - 1
        while pos >= 0:
            if ( my_list[pos] > temp):
                my_list[pos + 1] = my_list[pos]
                pos -= 1
            else:
                break
        my_list[pos + 1] = temp
                
    return my_list

lst = [1, 5, 63, 0, 2, 4, 3, 5, 88, 14, 4, 26]
print(lst)
print(insertSort(lst))
