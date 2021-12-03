def binary_search(array, search_value)

    # we're searching for can be. To start, the lower bound is the first 
    # value in the array, while the upper bound is the last value:
    # First we eatablish where the lower and upper bounds of where the value
    
    lower_bound = 0
    upper_bound = array.length - 1
    
    # We begin a loop in which we examine the middle-most value
    # between the upper and lower bounds: 

    while lower_bound <= upper_bound do
        # We find the midpoint between the lower and upper bounds:
        # (We don't have to worry about the result being non-integer
        # since in Ruby, the result of division of integers will always
        # be rounded down to the nearest integer

        midpoint = (upper_bound + lower_bound) / 2

        midpoint_value = array[midpoint]

        # If the value at midpoint is the value we're looking for, we're done.
        # If not, we change the lower or upper bound based on whether we need
        # to guess higher or lower

        if search_value == midpoint_value
            return midpoint
        elsif search_value < midpoint_value
            upper_bound = midpoint - 1
        elsif search_value > midpoint_value
            lower_bound = midpoint + 1
        end
    end

    # If we've narrowed the bounds until the've reached each other
    # that means that the value we're searching for is 
    # not contained within this array

    return nil
end



    

    