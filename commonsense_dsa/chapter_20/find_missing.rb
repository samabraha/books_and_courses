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
        if (n != x + 1) and n != x

            return n
        
        else
            x = n
        end
    end
end

puts find_missing_number([0, 1, 2, 3, 4, 6, 7, 8])
puts find_missing_number_2([0, 1, 2, 3, 4, 6, 7, 8])