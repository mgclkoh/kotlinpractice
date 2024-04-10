package jdbc.user.usinghelper

import java.util.*

fun main() {
    print("Get - id ? ")
    val id = Scanner(System.`in`).use { it.nextInt() }

    val user = UserDaoImpl().getById(id)
    println(user)
}