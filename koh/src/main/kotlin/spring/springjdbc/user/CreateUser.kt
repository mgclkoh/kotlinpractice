package spring.springjdbc.user

import jdbc.bcryptHashed
import jdbc.user.User2
import jdbc.user.UserDao
import spring.springjdbc.applicationContext
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import java.util.*

fun main() {
    val userDao = applicationContext.getBean(UserDao::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)

    print("Create - username(email) password first_name ? ")
    val user = Scanner(System.`in`).use {
        User2(username = it.next(), password = it.next().bcryptHashed,
            firstName = it.next())
    }

    try {
        userDao.getByUsername(user.username)
        log.debug("username이 존재합니다.")
    } catch (e: EmptyResultDataAccessException) { // username이 없을 경우
        val userCreated = userDao.create(user)
        log.info(userCreated.toString())
    }
}