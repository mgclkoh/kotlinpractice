package spring.springjdbc.user

import jdbc.user.UserDao
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import spring.springjdbc.applicationContext
import java.util.*

fun main() {
    val userDao = applicationContext.getBean(UserDao::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)

    print("Get - id ? ")
    val id = Scanner(System.`in`).use { it.nextInt() }

    try {
        val user = userDao.getById(id)
        log.info(user.toString())
    } catch (e: EmptyResultDataAccessException) {
        log.error("No user.")
    }
}