package spring.jpa.user

import org.slf4j.LoggerFactory
import spring.jpa.applicationContext
import java.util.*

fun main() {

    val userRepository =
        applicationContext.getBean(UserRepository::class.java)

    val log = LoggerFactory.getLogger({}.javaClass) // {}는 object만들어 자기자신을 나타나게함 == spring.jpa.user.FindUser

    print("Find User - id ? ")
    val id: Long = Scanner(System.`in`).use { it.nextLong() }

    val user = userRepository.findById(id)
    log.info(user.toString())
/*
    val userWithPosts = userRepository.findByIdWithPosts(id)
    log.info(userWithPosts?.posts.toString())
*/
}
