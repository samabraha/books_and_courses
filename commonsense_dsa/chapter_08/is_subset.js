function isSubset(array1, array2) {
    let largerArray;
    let smallerArray;

    if (array1.length > array2.length) {
        largerArray = array1;
	smallerArray = array2;
    } else {
        largerArray = array2;
        smallerArray = array1;
    }

    for (let i = 0; i < smallerArray.length; i++) {
        let foundMatch = false;

        for (let j = 0; j < largerArray.length; j++) {
            if (smallerArray[i] === largerArray[j]) {
                foundMatch = true;
                break;
            }
        }

        if (foundMatch === false) {
            return false;
        }
    }

    return true;
}

function hashSubset(array1, array2) {
    let largerArray;
    let smallerArray;
    let hashTable = {};


}

// let array1 = [1, 2, 3, 4, 5, 6]
// let array2 = [1, 2, 3, 4, 5, 7]

let array1 = ["Red", "Orange", "Yellow", "Green", "Blue"];
let array2 = ["Red", "Orange", "Yellow", "Green", "Lime"];

console.log("A: " + array1)
console.log("B: " + array2)

console.log(isSubset(array1, array2));
