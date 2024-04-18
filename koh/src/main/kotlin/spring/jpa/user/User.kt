package spring.jpa.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jdbc.formatted
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDateTime
//Table과 매핑하는 같은 이름의 클래스
@Entity
class User {
    //엔티티 쓰기위한 주요 키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0
    //SpingJpa는 그냥 default constructor 이용하므로 따로 생성하지 않아도 됨.
    lateinit var username: String
    lateinit var password: String
    lateinit var firstName: String
    lateinit var dateJoined: LocalDateTime
    lateinit var lastLogin: LocalDateTime
    // @OneToMany(mappedBy = "user") lateinit var posts: Set<Post>

    /**
     * @param password 평문 비밀번호
     * @return 평문 비밀번호와 user의 비밀번호가 매치하면 true 아니면 false
     */
    fun matchPassword(password: String): Boolean =
        BCrypt.checkpw(password, this.password)

    override fun toString(): String {
        return "User(id=$id, username='$username', firstName='$firstName', dateJoined=${dateJoined.formatted})"
    }
}