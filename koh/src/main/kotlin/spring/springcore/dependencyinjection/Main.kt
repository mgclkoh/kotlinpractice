package spring.springcore.dependencyinjection

fun main() {
    val jdbcHelper = JdbcHelper()
    val userDao = UserDao(jdbcHelper)
    val postDao = PostDao(jdbcHelper)

    userDao.listUsers()
    postDao.listPosts()
}