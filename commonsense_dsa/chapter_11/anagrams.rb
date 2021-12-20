def anagrams_of(string)
    return [string[0]] if string.length == 1

    collection = []

    substring_anagrams = anagrams_of(string[1, string.length - 1])

    substring_anagrams.each do |substring_anagram|
        (0..substring_anagram.length).each do |index|
            copy = String.new(substring_anagram)
            collection << copy.insert(index, string[0])
        end
    end

    return collection
end

puts anagrams_of("ABC")