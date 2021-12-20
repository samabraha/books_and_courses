def count_letters(arrays):
    if len(arrays) == 1:
        return len(arrays[0])
    return count_letters(arrays[1:]) + len(arrays[0])

para = ['There', 'is', 'a', 'numerical', 'sequence', 'known', 'as', '“Triangular', 'Numbers.”', 'The', 'pattern', 'begins', 'as', '1,', '3,', '6,', '10,', '15,', '21,', 'and', 'continues', 'onward', 'with', 'the', 'Nth', 'number', 'in', 'the', 'pattern,', 'which', 'is', 'N', 'plus', 'the', 'previous', 'number.', 'For', 'example,', 'the', '7th', 'number', 'in', 'the', 'sequence', 'is', '28,', 'since', 'its', '7', '(which', 'is', 'N)', 'plus', '21', '(the', 'previous', 'number', 'in', 'the', 'sequence).', 'Write', 'a', 'function', 'that', 'accepts', 'a', 'number', 'for', 'N', 'and', 'returns', 'the', 'correct', 'number', 'from', 'the', 'series.', 'That', 'is,', 'if', 'the', 'function', 'was', 'passed', 'the', 'number', '7,', 'the', 'function', 'would', 'return', '28.']

print(count_letters(para))




