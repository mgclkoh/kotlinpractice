package spring.springcore.beanfactory

// 빈을 미리 생성해서 갖고 있음
val beanFactory = mutableMapOf<String, Any>().apply {
    put("userDao", UserDao())
    put("postDao", PostDao())
}

fun main() {
    val userDao = beanFactory["userDao"] as UserDao
    userDao.listUsers()

    val postDao = beanFactory["postDao"] as PostDao
    postDao.listPosts()
}