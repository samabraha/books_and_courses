


def duplicates(listA, listB):
    holder = {}
    
    if (len(listA) > len(listB)):
        largerList = listA
        smallerList = listB
    else:
        largerList = listB
        smallerList = listA
    
    # for element in largerList:
    #     holder.setdefault(element, True)
    
    
    holder = dict(zip(largerList, [True] * len(largerList)))

    for element in smallerList:
        if element in holder:
            return True
    
    return False


a = ['a', 'b' 'c', 'h']
b = ['d', 'e', 'f', 'g', 'a']

print(duplicates(a, b))