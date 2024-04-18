package spring.jpa.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * Repository 인터페이스를 확장한 인터페이스의 구현체는
 * 개발자가 구현하지 않고 스프링이 구현한다.
 */
interface UserRepository : JpaRepository<User, Long> //인터페이스 생성,
      // CrudRepository에 필요한 인터페이스이므로 readonly=true)기본값으로 가지고 있음
 {
     fun findByUsername(username: String):User?   //엔티티 생성(username: String)

    /*@EntityGraph(attributePaths = ["posts"],
        type = EntityGraph.EntityGraphType.FETCH)
    @Query("select u from User u where u.id = ?1")
    fun findByIdWithPosts(id: Long): User?
    */
     //수동Query작성하므로, + nativeQuery쓰므로 schema.sql의 Table과 Column과 일치하게 유의!!
    @Modifying   //수동쿼리 정의
    @Transactional   //update하는 쿼리이므로 default값이므로 따로 선언X(readonly=false)
    @Query("update user set lastlogin=:lastLogin where id=:id",
        nativeQuery = true)  //native형태로 쓰기위한 선언(User가 아닌 -> user 사용)
                             //
    fun updateLastLogin(id: Long, lastLogin: LocalDateTime)


    @Modifying
    @Transactional
    @Query("update user set password=:password where id=:id",
        nativeQuery = true)
    fun changePassword(id: Long, password: String)
}