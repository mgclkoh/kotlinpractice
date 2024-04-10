package spring.springjdbc.post

import jdbc.post.PostDao
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import spring.springjdbc.applicationContext
import java.util.*

fun main() {
    val postDao = applicationContext.getBean(PostDao::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)

    print("Get post - id ? ")
    val id = Scanner(System.`in`).use { it.nextInt() }

    try {
        val post = postDao.getById(id)
        log.info(post.toString())
    } catch (e: EmptyResultDataAccessException) {
        log.error("No post.")
    }
}