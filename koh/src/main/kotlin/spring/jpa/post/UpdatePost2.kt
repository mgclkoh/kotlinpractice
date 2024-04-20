package spring.jpa.post

import org.slf4j.LoggerFactory
import spring.jpa.applicationContext
import java.util.*
//UpdatePost1과 동일하나 방법만 다름 count를 써 좀더 간단화?
fun main() {
    val postRepository = applicationContext.getBean(PostRepository::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)

    print("Update Post - id//title//content// ? ")
    val scanner = Scanner(System.`in`).useDelimiter("//")
    val id = scanner.nextLong()
    val title = scanner.next()
    val content = scanner.next()
    scanner.close()

    val count = postRepository.update(id = id, title = title, content = content)
    if (count >= 1) log.info("Update complete.")
    else log.error("No post. ")
}