def bubble_sort(lst):
    unsorted_until_index = len(lst) - 1
    sorted = False

    while not sorted:
        sorted = True
        for i in range(unsorted_until_index):
            if lst[i] > lst[i + 1]:
                lst[i], lst[i + 1] = lst[i + 1], lst[i]
                sorted = False
        unsorted_until_index -= 1
    return lst

my_list = [15, 45, 42, 69, 48, 11, 4, 161, 3453, 1, -541, 15, -20]

print(my_list)
new_list = bubble_sort(my_list)
print(new_list)