package spring.jpa.user

import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import spring.jpa.applicationContext
import java.util.*

fun main() {
    val userRepository = applicationContext.getBean(UserRepository::class.java)
    val userService = applicationContext.getBean(UserService::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)

    print("Delete user - username(email) password ? ")
    val (username, password) = Scanner(System.`in`).use {
        arrayOf(it.next(), it.next())
    }

    val user = userService.login(username, password) ?: return
    try {
        userRepository.deleteById(user.id)
        log.info("삭제했습니다.")
    } catch (e: DataIntegrityViolationException) {
        log.error("post에 올린 글이 있어서 삭제할 수 없습니다.")
    }
}