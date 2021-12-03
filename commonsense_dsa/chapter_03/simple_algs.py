def print_list(say_this, my_list):
    for thing in my_list:
        print(f"{say_this}: {thing}")

persons = ['Kant', 'Descartes', 'Abelard', 'Avicena', 'Thales']
# print_list("Hello", persons)

def is_prime(number):
    for i in range(2, number):
        if number % i == 0:
            return False
    return True


for i in range(1, 5000):
    if is_prime(i): print(i, end=' ')
print()

