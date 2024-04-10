package spring.springjdbc.post

import jdbc.post.Post
import jdbc.post.PostDao
import org.slf4j.LoggerFactory
import spring.springjdbc.applicationContext
import java.util.*

fun main() {
    val postDao = applicationContext.getBean(PostDao::class.java)
    val log = LoggerFactory.getLogger(Post::class.java)

    print("Update post - id//title//content// ? ")
    val post = Scanner(System.`in`).useDelimiter("//").use {
        Post(id = it.nextInt(), title = it.next(), content = it.next())
    }

    val count = postDao.update(post)
    if (count >= 1) log.info("Update complete.")
    else log.error("No post.")
}