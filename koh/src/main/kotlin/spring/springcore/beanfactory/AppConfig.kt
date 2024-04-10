package spring.springcore.beanfactory

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// 빈 구성 클래스
@Configuration
open class AppConfig {
    @Bean
    open fun userDao(): UserDao = UserDao()

    @Bean
    open fun postDao(): PostDao = PostDao()
}

// AppConfig 구성에 따라 빈을 생성해서 갖고 있음
val applicationContext: ApplicationContext =
    AnnotationConfigApplicationContext(AppConfig::class.java)

fun main() {

    println("--- beans ---")  //생성 beans들 출력
    applicationContext.beanDefinitionNames.forEach(::println)
    println("-------------")

    // 이름으로 찾기
    val userDao: UserDao = applicationContext.getBean("userDao") as UserDao
    userDao.listUsers()

    // 타입으로 찾기
    val postDao: PostDao = applicationContext.getBean(PostDao::class.java)
    postDao.listPosts()
}