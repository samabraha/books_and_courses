def sum_all(low, high)
    if low == high
        return high
    end
    return high + sum_all(low, high - 1)
end

puts sum_all(1, 100)