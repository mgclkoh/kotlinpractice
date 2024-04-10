package stdlibs

/*
 * https://kotlinlang.org/docs/scope-functions.html
 */

import java.io.File

data class Person4(var name: String = "", var age: Int = 0)

fun main() {
    // let : lambda의 결과를 리턴. 주로 다른 함수에 인자로 넣어서 실행할 때 사용
    val adam = Person4("Adam", 20)
    println("name = ${adam.name}")
    println("age = ${adam.age}")

    Person4("Eve", 17).let {
        println("name = ${it.name}")
        println("age = ${it.age}")
    }

    Person4("Peter", 21).let { person2 ->
        println("name = ${person2.name}")
        println("age = ${person2.age}")
    }

    // apply : context object를 리턴. 주로 오브젝트를 구성할 때 사용
    val andre = Person4()
    andre.name = "Andre"
    andre.age = 22

    val jacob = Person4().apply {
        this.name = "Jacob"
        this.age = 23  //this는 자동이므로 생략가능
    }
    println(jacob)  //.let(::println)

    // use : AutoCloseable 객체를 자동 close()
    File("README.md").bufferedReader().use { reader ->
        reader.forEachLine(::println)
    }
    /* scopefuction 응용
    // use : AutoCloseable 객체를 자동 close()
    File("README.md").bufferedReader().use {
        it.forEachLine(::println)
    }
    */
}