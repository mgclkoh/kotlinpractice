package spring.jpa.user

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime


//로그인관련 클래스 만들어놔 계속 쓰기 위해
@Service
class UserService(private val userRepository: UserRepository) {   //UserService에 UserRepository(Proxy) 주입
                                                                  // + bean 으로 ComponentScan가짐.
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun login(username: String, password: String): User? {
        val user: User? = userRepository.findByUsername(username)
        return if (user?.matchPassword(password) == true) {
            val lastLogin = LocalDateTime.now()
            userRepository.updateLastLogin(user.id, lastLogin)
            user.apply { this.lastLogin = lastLogin }
        } else {
            log.error("Wrong username or password.")
            null
        }
    }
}