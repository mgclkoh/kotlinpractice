package spring.springjdbc.post

import jdbc.post.Post
import jdbc.post.PostDao
import spring.springjdbc.applicationContext
import java.util.*

fun main(){
    val postDao = applicationContext.getBean(PostDao::class.java)
    println("title//content//userId//first_name")
    val post = Scanner(System.`in`).useDelimiter("//").use {
        Post (
            title = it.next(),
            content = it.next(),
            userId = it.nextInt(),
            firstName = it.next()
            )
    }
    postDao.create(post)
    println("저장 완료")

}
