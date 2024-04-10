package spring.jpa.user

//import org.springframework.data.jpa.repository.EntityGraph
//import org.springframework.data.jpa.repository.Modifying
//import org.springframework.data.jpa.repository.Query
import jdbc.user.User2
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository 인터페이스를 확장한 인터페이스의 구현체는
 * 개발자가 구현하지 않고 스프링이 구현한다.
 */
interface UserRepository : JpaRepository<User2, Long>
/* {

    @EntityGraph(attributePaths = ["posts"],
        type = EntityGraph.EntityGraphType.FETCH)
    @Query("select u from User u where u.id = ?1")
    fun findByIdWithPosts(id: Long): User?

    fun findByUsername(username: String): User?

    @Modifying
    @Transactional
    @Query("update User set password=:password where id=:id")
    fun changePassword(id: Long, password: String)
}*/