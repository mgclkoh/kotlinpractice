package spring.jpa.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

//인터페이스 PostRepository생성 (Post에 jpa 전용 인터페이스 쓰겠다.)
interface PostRepository : JpaRepository<Post, Long> {

    // 수동으로 만드는 수정 쿼리 쓰기위해
    @Modifying
    @Transactional
    //쿼리의 파라미터 지정 ':' + nativequery쓰기 위한 정의
    @Query("update post set title=:title, content=:where id=:id", nativeQuery = true)
    //수정하는 쿼리 부분 위의 3개의 파라미터를 받음 반환: 업데이트된 행의 수(Int)
    fun update(id: Long, title: String, content: String): Int
}
