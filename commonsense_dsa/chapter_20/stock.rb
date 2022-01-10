def find_greatest_profit(array)
    buy_price = array[0]
    greatest_profit = 0
    array.each do |price|
        potential = price - buy_price

        if price < buy_price
            buy_price = price
        elsif potential > greatest_profit
            greatest_profit = potential
        end
    end

    return greatest_profit
end

# puts find_greatest_profit([9, 2, 3, 5])

def greatest_product(array)
    highest_number = -Float::INFINITY
    second_highest_number = -Float::INFINITY
    lowest_number = Float::INFINITY
    second_lowest_number = Float::INFINITY

    array.each do |number|
        if number >= highest_number
            second_highest_number = highest_number
            highest_number = number
        elsif number > second_highest_number
            second_highest_number = number
        end

        if number <= lowest_number
            second_lowest_number = lowest_number
            lowest_number = number
        elsif number > lowest_number && number < second_lowest_number
            second_lowest_number = number
        end
    end

    greatest_product_from_highest = highest_number * second_highest_number
    greatest_product_from_lowest = lowest_number * second_lowest_number

    if greatest_product_from_highest > greatest_product_from_lowest
        return greatest_product_from_highest
    else
        return greatest_product_from_lowest
    end

end

puts greatest_product([-3, -2, 0, 4, 8, 1, 5])

