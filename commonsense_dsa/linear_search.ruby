def linear_search(array.search_value)

    #We iterate through every element in the array
    array.each_with_index do |element, index|

        #If we find the value we are looking for we return its index
        if element == search_value
            return index

        #If we reach an element that is greater than the value
        #we are looking for, we can exit the loop early
        if element > search_value
            break
        
        end
    end

    return nil
end
