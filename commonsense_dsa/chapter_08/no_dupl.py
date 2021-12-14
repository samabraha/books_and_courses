def first_non_duplicated(word):
    map = {}
    duplicates = {}
    for l in word:
        if l not in map:
            map.setdefault(l, True)
        else:
            duplicates.setdefault(l, True)
    print(map.keys())
    return duplicates.keys()


word = input("Enter word: ")

print(first_non_duplicated(word))