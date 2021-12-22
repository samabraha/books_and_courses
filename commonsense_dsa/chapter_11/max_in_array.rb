def max(array)
    puts "R"
    return array[0] if array.length == 1

    saved = max(array[1, array.length - 1])

    if array[0] > saved
        return array[0]
    else
        saved
    end
end

puts max([1, 5, 3, 4])