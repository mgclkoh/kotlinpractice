package stdlibs

/*
 * https://kotlinlang.org/docs/extensions.html
 */

fun main() {
    val list = mutableListOf(10, 20, 30)
    swapStatic(list, 1, 2)
    println(list) // [10, 30, 20]

    list.swapExension(0, 2)
    println(list) // [20, 30, 10]

    println(list.lastElement) // 10
}

/**
 * Top level 함수 (static)
 */
fun <T> swapStatic(list: MutableList<T>, index1: Int, index2: Int) {
    val tmp = list[index1]
    list[index1] = list[index2]
    list[index2] = tmp
}

/**
 * Extension function (확장 함수)
 */
fun <T> MutableList<T>.swapExension(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

/**
 * Extension property (확장 속성)
 */
val <T> List<T>.lastElement: T get() = this[size - 1]
/*  위의 val와 동일
fun <T> List<T>.getLastElement(): T {
    return this[size -1]
}
*/