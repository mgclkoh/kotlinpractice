package jdbc.user.usinghelper

import jdbc.bcryptHashed
import jdbc.user.User2
import java.util.*

fun main() {
    print("Change password - username oldPassword newPassword? ")
    val scanner = Scanner(System.`in`)
    val username = scanner.next()
    val oldPassword = scanner.next()
    val newPassword = scanner.next()
    scanner.close()

    val userDao = UserDaoImpl()
    val user2: User2? = userDao.getByUsername(username)
    if (user2?.matchPassword(oldPassword) == true) {
        userDao.changePassword(user2.id, newPassword.bcryptHashed)
        println("Password changed.")
    } else {
        println("Wrong username or password")
    }
}