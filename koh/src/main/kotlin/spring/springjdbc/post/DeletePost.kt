package spring.springjdbc.post

import jdbc.post.PostDao
import org.slf4j.LoggerFactory
import spring.springjdbc.applicationContext
import java.util.*

fun main() {
    val postDao = applicationContext.getBean(PostDao::class.java)
    val log = LoggerFactory.getLogger({}.javaClass)

    print("Delete post - id ? ")
    val id = Scanner(System.`in`).use { it.nextInt() }

    val count = postDao.deleteById(id)
    if (count >= 1) log.info("$id 번 글을 삭제했습니다.")
    else log.info("$id 번 글이 없습니다.")
}