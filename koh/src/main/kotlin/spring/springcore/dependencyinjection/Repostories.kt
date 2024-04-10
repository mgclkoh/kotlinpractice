package spring.springcore.dependencyinjection

class JdbcHelper {
    init {
        println("jdbcHelper created.")
    }

    fun list(sql: String) {
        println("$sql 실행")
    }
}

class UserDao(private val jdbcHelper: JdbcHelper) {
    init {
        println("userDao created.")
    }

    fun listUsers() {
        jdbcHelper.list("사용자 목록 가져오기")
    }
}

class PostDao(private val jdbcHelper: JdbcHelper) {
    init {
        println("postDao created.")
    }

    fun listPosts() {
        jdbcHelper.list("포스트 목록 가져오기")
    }
}