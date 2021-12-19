def reverse_str(word):
    print("|", end="")
    if len(word) <= 1:
        print("")
        return word
    return word[len(word) - 1]+ reverse_str(word[1: -1]) + word[0]

def other_reverse(word):
    if len(word) == 1:
        return word
    return other_reverse(word[1: -1]) + word[0]

print(reverse_str("ABCDEFGHIJABCDEFGHIJ"))
# print(other_reverse("ABCDEFGHIJ"))
