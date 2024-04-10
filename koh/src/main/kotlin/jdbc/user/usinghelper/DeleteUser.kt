package jdbc.user.usinghelper

import jdbc.user.User2
import java.util.*

fun main() {
    print("Delete - username password ? ")
    val scanner = Scanner(System.`in`)
    val username = scanner.next()
    val password = scanner.next()
    scanner.close()

    val userDao = UserDaoImpl()
    val user: User2? = userDao.getByUsername(username)
    if (user?.matchPassword(password) == true) {
        userDao.deleteById(user.id)
        println("삭제했습니다.")
    } else {
        println("Wrong username or password.")
    }
}