def reverse_string(string)
    puts "|"
    return string[0] if string.length == 1

    return reverse_string(string[1, string.length - 1]) + string[0]
end
puts reverse_string("ABCDEFGHIJABCDEFGHIJ")
