println("Hello from free-floating code")
// Thread.sleep(1000)
println("I'm back")

try {
    // nofluff()
} catch (ex:Exception) {
    val trace = ex.getStackTrace()
    println(trace[0])
    println(trace[1])
}

fun nofluff() {
    println("nofluff called...")

    throw RuntimeException("Oops")
}

// fun compute(n: Int) {
//     println(4)
// }

// println("hi" == "hi")
// println("hi" == "Hi")
// println(null == "hi")
// println("hi" == null)
// println(null == null)

println("*".repeat(25))

val price = 12.25
val taxRate = 0.08

val output = "The amount $price after tax comes to $${price * (1 + taxRate)}"
val disclaimer = "The amount is in US$, that's right in \$only"


println(output)
println(disclaimer)