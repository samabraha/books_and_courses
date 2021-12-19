def count_x(word, letter="X"):
    # print(word, letter)
    # if len(word) <= 1 and word == letter:
    #     return 1
    # elif len(word) <= 1 and word != letter:
    #     return 0
    
    if len(word) < 1:
        return 0

    if (word[0] == letter):
        return 1 + count_x(word[1:], letter)
    else:
        return count_x(word[1:], letter)

wrd = "AABABCABCDABCDEABCDEFABCDEFG"
# print("W:", wrd[0: ])
print(count_x(wrd, "F"))
    
