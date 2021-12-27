fun main() {
    val numberNames = hashMapOf<Int, String>(
        1 to "One",2 to "Two",3 to "Three",
        4 to "Four",5 to "Five",6 to "Siz"
    )
    for ((k, v) in numberNames) {
        print("Key: $k maps to $v")
    }
}
