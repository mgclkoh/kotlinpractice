package jdbc.post

import jdbc.Page

interface PostDao {
    fun list(page: Page): List<Post>

    fun getById(id: Int): Post?

    fun create(post: Post): Post  //Int

    fun update(post: Post): Int

    fun deleteById(id: Int): Int
}