package spring.jpa.post

import org.slf4j.LoggerFactory
import spring.jpa.applicationContext
import java.util.*

fun main() {
    val postRepository = applicationContext.getBean(PostRepository::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)

    print("Fine post - id ? ")
    val id: Long = Scanner(System.`in`).use { it.nextLong() }
    /*
    id에 맞는 Post에 post 변수에 저장
    post 변수는 Optional 객체로 지정,
    게시물이 존재하는 경우 해당 Post포함, 존재하지 않는 경우에 비어있음
    */
    val post: Optional<Post> = postRepository.findById(id)
    log.info(post.toString())
}