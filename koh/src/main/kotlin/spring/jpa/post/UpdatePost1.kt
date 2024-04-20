package spring.jpa.post

import jakarta.persistence.NoResultException
import org.slf4j.LoggerFactory
import spring.jpa.applicationContext
import java.time.LocalDateTime
import java.util.*
    /*
    applicationContext는 spring앱의 bean, configuration관리하는 인터페이스임
    getBean(PostRepository::class.java)는 PostRepository타입의 빈 가져오기위한 메소드
    applicationContext에서 빈들 찾아와 반환
    */
fun main() {
    val postRepository = applicationContext.getBean(PostRepository::class.java)
    val log = LoggerFactory.getLogger({}.javaClass) //log에 이 클래스자체로 이름 출력

    print("Update Post - id//title//content// ? ")
    /*Scanner 객체를 생성하여 사용자의 입력받음
    입력값을 "//" 기준으로 구분하기 위해 useDelimiter("//")를 설정함
    */
    val scanner = Scanner(System.`in`).useDelimiter("//")
    val id = scanner.nextLong()
    val title = scanner.next()  //제목을 문자열 형식으로 받고 다음으로
    val content = scanner.next() //내욜을 문자열 형식으로 받고 다음으로
    scanner.close()  //위의 입력 다 받고 scanner닫음

    //DATABASE에서 글 가져와 속성 일부수정 후 저장함
    val post = postRepository.findById(id)  //post에 CRUD에 있는 함수로 id찾아 가져옴
        .orElseThrow { NoResultException("Post not found") }  //결과 없을경우 출력
    post.title = title
    post.content = content
    post.lastModified = LocalDateTime.now()

    postRepository.save(post)  //마지막에 수정사항 가져아 새로이 저장
    log.info("Update complete.")
}