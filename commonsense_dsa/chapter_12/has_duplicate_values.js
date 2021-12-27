function hasDuplicateValues(array) {
    array.sort((a, b) => (a < b) ? -1 : 1);

    for (let i = 0; i < array.length - 1; i++) {
        if (array[i] == array[i + 1]) {
            return true;
        }
    }

    return false;
}

function greatestProductOfThree(array) {
    array.sort((a, b) => (a < b) ? -1 : 1);

    let product = 1;
    if (array.length < 3) {
        for (let i = 0; i < array.length; i++) {
            product = product * array[i];          
        }
    } else {
        product = array[array.length - 1] * array[array.length - 2] * array[array.length - 3];
    }
    

    return product;
}