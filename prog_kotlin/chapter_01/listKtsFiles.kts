#!/home/sam/.sdkman/candidates/kotlin/current/bin/ kotlinc-jvm -script


println("Welcome to my script")
java.io.File(".")
    .walk()
    .filter { file -> file.extension == "kts" }
    .forEach { println(it) }