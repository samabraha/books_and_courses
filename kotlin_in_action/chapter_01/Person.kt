data class Person(val name: String, age? : Int = null)

fun main(args: Array<String>) {
    val people = listOf(
        Person("Alice"), 
        Person("Bob", 29),
        Person("Carla", 33))

    val oldest = people.maxBy { it.age ?: 0 }
    println("The oldest is $oldest")
}