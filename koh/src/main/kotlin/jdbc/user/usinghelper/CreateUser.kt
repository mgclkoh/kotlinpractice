package jdbc.user.usinghelper

import jdbc.bcryptHashed
import jdbc.user.User2
import java.util.*

fun main() {
    print("Create - username(username) password first_name ? ")
    val user = Scanner(System.`in`).use {
        User2(username = it.next(), password = it.next().bcryptHashed,
            firstName = it.next())
    }

    val userDao = UserDaoImpl()
    if (userDao.getByUsername(user.username) == null) { // username 없을 경우
        val userCreated = userDao.create(user)
        println(userCreated)
    } else {
        println("username이 존재합니다.")
    }
}