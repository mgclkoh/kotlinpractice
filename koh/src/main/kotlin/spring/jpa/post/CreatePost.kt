package spring.jpa.post

import org.slf4j.LoggerFactory
import spring.jpa.applicationContext
import spring.jpa.user.UserService
import java.time.LocalDateTime
import java.util.*

fun main() {
    val postRepository = applicationContext.getBean(PostRepository::class.java)
    //user.UserService에 있는 bean들 가져오기 위해
    val userService = applicationContext.getBean(UserService::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)

    print("Create post - username(email)//password//title//content// ? ")
    val  (username, password, title, content) = Scanner(System.`in`).useDelimiter(
        "//").use {  //use함수이용하여 자동으로 끝내기 위해
            arrayOf(it.next().trim(), it.next().trim(), it.next().trim(), it.next().trim())
    } //.trim()함수는 앞뒤의 공백을 제거함, 각각 순서대로 username, password, title, content 변수에
    val user = userService.login(username, password) ?: return
    // ?:는 왼쪽 연산자가 null이 아니면 값반환, null이면 오른족 연산자값(종료 후 호출된 것으로) 반환
    val post = Post().apply {
        this.title = title
        this.content = content
        this.user = user
        pubDate = LocalDateTime.now()
        lastModified = LocalDateTime.now()
    }

    postRepository.save(post)
    log.info(post.toString())  //post객체의 정보를 로그로 출력
}