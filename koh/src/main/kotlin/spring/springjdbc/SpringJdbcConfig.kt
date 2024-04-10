package spring.springjdbc

import org.mariadb.jdbc.MariaDbDataSource
import org.springframework.context.annotation.*
import org.springframework.core.env.Environment
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
open class SpringJdbcConfig(private val env: Environment) {
    @Bean
    open fun dataSource() = MariaDbDataSource(env.getProperty("db.url"))

    @Bean
    open fun nameParameterJdbcTemplate() =
        NamedParameterJdbcTemplate(dataSource())
}

val applicationContext =
    AnnotationConfigApplicationContext(SpringJdbcConfig::class.java)