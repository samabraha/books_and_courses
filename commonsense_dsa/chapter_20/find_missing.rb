def find_missing_number(array)
    full_sum = 0
    (1..array.length).each do |n|
        full_sum += n
    end

    current_sum = 0

    array.each do |n|
        current_sum += n
    end

    return full_sum - current_sum
end


def find_missing_number_2(array)
    x = array[0]
    array.each do |n|
        if x == n or x == n - 1
            x = n
        else
            return x + 1
        end
    end
end

# puts find_missing_number([0, 1, 2, 3, 4, 6, 7, 8])
# puts find_missing_number_2([3, 4, 5, 6, 8])

def longest_sequence_length(array)
    hash_table = {}
    longest_sequence_len = 0

    array.each do |n|
        hash_table[n] = true
    end


    array.each do |n|
        if !hash_table[n - 1]

            current_sequence_length = 1
            current_num = n

            while hash_table[current_num + 1]
                current_num += 1
                current_sequence_length += 1

                if current_sequence_length > longest_sequence_len
                    longest_sequence_len = current_sequence_length
                end
            end
        end
    end

    return longest_sequence_len
end

puts longest_sequence_length([4, 12, 14, 6, 11, 8, 10, 13])