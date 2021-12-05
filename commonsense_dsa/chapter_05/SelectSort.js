function swap(array, a, b) {
    const temp = array[a];
    array[a] = array[b];
    array[b] = temp;
}

function selectionSort(array) {
    for (let i = 0; i < array.length - 1; i++) {
        let lowestIndex = i;
        for (let j = i + 1; j < array.length; j++) {
            if (array[j] < array[lowestIndex]) {
                lowestIndex = j;
            }
        }
        if (i != lowestIndex) {
            swap(array, i, lowestIndex);
        }
    }
    return array;
}

myArray = [32, 4, 74, 120, 64, 11, -5, 12, 10, 10, 101];
console.log(myArray);
selectionSort(myArray);
console.log(myArray);