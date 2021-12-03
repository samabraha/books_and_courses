function hasDuplicateValues(array) {
    let existingNumbers = [];

    for (let i = 0; i < array.length; i++) {
        if (existingNumbers[array[i]] === 1) {
            return true;
        } else {
            existingNumbers[array[i]] = 1;
        }
    }

    console.log(existingNumbers);
    return false;
}

let myList = [2, 50, 306464, 4];
console.log(hasDuplicateValues(myList));
console.log(myList);

myList = [2, 5, 3, 4, 7];
console.log(myList);
console.log(hasDuplicateValues(myList));

