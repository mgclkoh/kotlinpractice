package spring.jpa.user

import jdbc.bcryptHashed
import org.slf4j.LoggerFactory
import spring.jpa.applicationContext
import java.time.LocalDateTime
import java.util.*

fun main() {
    val userRepository = applicationContext.getBean(UserRepository::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)   //Log의 이름을 이 클래스 자체로 선언한다

    print("Create user - username(email) password firstName ? ")
    val user = Scanner(System.`in`).use {
        User().apply {
            username = it.next()
            password = it.next().bcryptHashed
            firstName = it.next()
            dateJoined = LocalDateTime.now()
            lastLogin = dateJoined
        }
    }

    if (userRepository.findByUsername(user.username) == null) {
        userRepository.save(user)
        log.info(user.toString())
    } else {
        log.debug("username이 존재합니다.")
    }
}