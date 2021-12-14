def largest_product(array)
    largest_product_so_far = array[0] * array[1] * array[2]
    
    i = 0
    while i < array.length
        j = i + 1
        while j < array.length
            k = j + 1
            while k < array.length
                product = array[i] * array[j] * array[k]
                if product > largest_product_so_far
                    largest_product_so_far = product
                end
                k += 1
            end
            j += 1
        end
        i += 1
    end

    return largest_product_so_far
end

puts(largest_product([1, 2, 7, 4, 5]))