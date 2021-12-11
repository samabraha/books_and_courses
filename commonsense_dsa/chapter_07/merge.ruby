def merge(array_1, array_2)
    new_array = []
    array_1_pointer = 0
    array_2_pointer = 0

    while array_1_pointer < array_1.length || array_2_pointer < array_2.length
        if !array_1[array_1_pointer]
            new_array << array_2[array_2_pointer]
            array_2_pointer += 1
        elsif !array_2[array_2_pointer]
            new_array << array_1[array_1_pointer]
            array_1_pointer += 1
        elsif array_1[array_1_pointer] < array_2[array_2_pointer]
            new_array << array_1[array_1_pointer]
            array_1_pointer += 1
        else
            new_array << array_2[array_2_pointer]
            array_2_pointer += 1
        end
    end

    return new_array
end

arr_1 = [3, 4, 5, 2]
arr_2 = [1, 6, 8, 10]
print(merge(arr_1, arr_2))
puts()