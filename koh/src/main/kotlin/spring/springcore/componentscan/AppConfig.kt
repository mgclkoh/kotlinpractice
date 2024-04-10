package spring.springcore.componentscan

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan // 현재 패키지 이하로 @Component annotated 빈 생성
open class AppConfig

// AppConfig 구성과 component scan을 해서 빈을 생성해서 갖고 있음
val applicationContext: ApplicationContext =
    AnnotationConfigApplicationContext(AppConfig::class.java)

fun main() {
    val userDao: UserDao = applicationContext.getBean(UserDao::class.java)
    userDao.listUsers()

    val postDao: PostDao = applicationContext.getBean(PostDao::class.java)
    postDao.listPosts()
}