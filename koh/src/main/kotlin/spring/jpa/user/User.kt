package spring.jpa.user

import jakarta.persistence.*
import jdbc.formatted
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDateTime

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0
    lateinit var username: String
    lateinit var password: String
    lateinit var firstName: String
    lateinit var dateJoined: LocalDateTime
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