package spring.springjdbc.user

import jdbc.bcryptHashed
import jdbc.user.UserDao
import spring.springjdbc.applicationContext
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import java.util.*

fun main() {
    val userDao = applicationContext.getBean(UserDao::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)

    print("Change password - username oldPassword newPassword? ")
    val scanner = Scanner(System.`in`)
    val username = scanner.next()
    val oldPassword = scanner.next()
    val newPassword = scanner.next()
    scanner.close()

    try {
        val user = userDao.getByUsername(username)
        if (user?.matchPassword(oldPassword) == true) {
            userDao.changePassword(user.id, newPassword.bcryptHashed)
            log.info("Password changed.")
        } else {
            log.debug("Wrong password.")
        }
    } catch (e: EmptyResultDataAccessException) {
        log.error("No user.")
    }
}