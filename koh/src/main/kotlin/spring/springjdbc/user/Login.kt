package spring.springjdbc.user

import jdbc.user.UserDao
import spring.springjdbc.applicationContext
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import java.util.*

fun main() {
    val userDao = applicationContext.getBean(UserDao::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)

    print("Login - username(email) password ? ")
    val scanner = Scanner(System.`in`)
    val username = scanner.next()
    val password = scanner.next()
    scanner.close()

    try {
        val user = userDao.getByUsername(username)
        if (user?.matchPassword(password) == true) log.info(user.toString())
        else log.debug("Wrong password.")
    } catch (e: EmptyResultDataAccessException) {
        log.error("No user.")
    }
}