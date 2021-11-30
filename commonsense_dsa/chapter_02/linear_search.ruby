def linear_search(array, search_value)

    #We iterate through every element in the array
    array.each_with_index do |element, index|

        #If we find the value we are looking for we return its index
        if element == search_value
            puts ("Found " + search_value.to_s + " at " + index.to_s)
            return index    

        #If we reach an element that is greater than the value
        #we are looking for, we can exit the loop early
        elsif element > search_value
            break
        end
    end

    puts "Not found!"
    return nil
end

myArra = [14, 51, 73, 80, 91, 96, 102, 104, 133, 157, 160]

print "Enter a number: "
search_for = gets.to_i

linear_search(myArra, search_for)