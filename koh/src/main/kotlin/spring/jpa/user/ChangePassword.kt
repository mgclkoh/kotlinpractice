package spring.jpa.user

import jdbc.bcryptHashed
import org.slf4j.LoggerFactory
import spring.jpa.applicationContext
import java.util.*

fun main() {
    val userRepository = applicationContext.getBean(UserRepository::class.java)   //ApplicationContext에서 Repository꺼내 함수 호출하여 동작
    val userService = applicationContext.getBean(UserService::class.java)    //ApplicationContext에서 userService에
                                                                             // Repository 미리 선언했으므로, 꺼내 함수 호출하여 동작
    val log = LoggerFactory.getLogger({}.javaClass)   //Log의 이름은 이 클래스자체로 선언

    print("Change password - username oldPassword newPassword? ")
    val (username, oldPassword, newPassword) = Scanner(System.`in`).use {
        arrayOf(it.next(), it.next(), it.next())
    }

    val user = userService.login(username, oldPassword) ?: return
    userRepository.changePassword(user.id, newPassword.bcryptHashed)
    log.info("Password changed.")
}