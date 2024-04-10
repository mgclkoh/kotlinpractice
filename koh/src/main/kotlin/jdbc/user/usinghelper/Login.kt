package jdbc.user.usinghelper

import jdbc.user.User2
import java.util.*

fun main() {
    print("Login - username password ? ")
    val scanner = Scanner(System.`in`)
    val username = scanner.next()
    val password = scanner.next()
    scanner.close()

    val user: User2? = UserDaoImpl().getByUsername(username)
    if (user?.matchPassword(password) == true) println(user)
    else println("Wrong username or password.")
}