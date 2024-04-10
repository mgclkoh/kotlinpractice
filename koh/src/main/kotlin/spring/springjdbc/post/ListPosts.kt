package spring.springjdbc.post

import jdbc.Page
import jdbc.post.PostDao
import spring.springjdbc.applicationContext
import java.util.*

fun main() {
    val postDao = applicationContext.getBean(PostDao::class.java)
    print("List - pageNumber pageSize ? ")
    val page = Scanner(System.`in`).use { Page(it.nextInt(), it.nextInt()) }

    val postList = postDao.list(page)
    postList.forEach(::println)
}