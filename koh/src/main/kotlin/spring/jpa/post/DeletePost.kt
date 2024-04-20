package spring.jpa.post

import org.slf4j.LoggerFactory
import spring.jpa.applicationContext
import java.util.*

fun main() {
    val postRepository = applicationContext.getBean(PostRepository::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)

    print("Delete post -id ? ")
    val id = Scanner(System.`in`).use { it.nextLong() }
    //삭제하는 crudRepository 이용
    postRepository.deleteById(id)
    log.info("Delete complete.")
}