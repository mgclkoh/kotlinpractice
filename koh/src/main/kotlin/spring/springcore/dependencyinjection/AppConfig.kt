package spring.springcore.dependencyinjection

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// 빈 구성 클래스. Constructor injection 사용
@Configuration
open class AppConfig {
    @Bean
    open fun jdbcHelper(): JdbcHelper = JdbcHelper()

    @Bean
    open fun userDao(): UserDao = UserDao(jdbcHelper())

    @Bean
    open fun postDao(jdbcHelper: JdbcHelper): PostDao = PostDao(jdbcHelper)
}

// AppConfig 구성에 따라 빈을 생성해서 갖고 있음
val applicationContext: ApplicationContext =
    AnnotationConfigApplicationContext(AppConfig::class.java)

fun main() {
    val userDao: UserDao = applicationContext.getBean(UserDao::class.java)
    userDao.listUsers()

    val postDao: PostDao = applicationContext.getBean(PostDao::class.java)
    postDao.listPosts()
}