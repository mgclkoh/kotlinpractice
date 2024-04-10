package spring.jpa.user

import spring.jpa.applicationContext

fun main() {

    val userRepository =
        applicationContext.getBean(UserRepository::class.java)
    val user = userRepository.findById(2)
    println(user)
    /* val log = LoggerFactory.getLogger({}.javaClass)

    print("Find User - id ? ")
    val id: Long = Scanner(System.`in`).use { it.nextLong() }
    val user = userRepository.findById(id)
    log.info(user.toString())

    val userWithPosts = userRepository.findByIdWithPosts(id)
    log.info(userWithPosts?.posts.toString())
    */
}
