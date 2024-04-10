package spring.springcore.componentscan

import org.springframework.stereotype.Component

@Component
class JdbcHelper {
    init {
        println("jdbcHelper created.")
    }

    fun list(sql: String) {
        println("$sql 실행")
    }
}

@Component
class UserDao(private val jdbcHelper: JdbcHelper) {
    init {
        println("userDao created.")
    }

    fun listUsers() {
        jdbcHelper.list("사용자 목록 가져오기")
    }
}

@Component
class PostDao(private val jdbcHelper: JdbcHelper) {
    init {
        println("postDao created.")
    }

    fun listPosts() {
        jdbcHelper.list("포스트 목록 가져오기")
    }
}