def add_until_100(array)
    return 0 if array.length == 0
    
    memo = add_until_100(array[1, array.length - 1])
    if array[0] + memo > 100
        return memo
    else
        return array[0] + memo
    end
end

puts add_until_100([11, 22, 33, 5, 6, 7, 80, 9])