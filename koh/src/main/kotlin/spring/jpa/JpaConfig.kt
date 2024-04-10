package spring.jpa

import jakarta.persistence.EntityManagerFactory
import org.mariadb.jdbc.MariaDbDataSource
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
open class JpaConfig(private val env: Environment) {
    @Bean
    open fun dataSource() = MariaDbDataSource(env.getProperty("db.url"))

    @Bean
    open fun entityManagerFactory() =
        LocalContainerEntityManagerFactoryBean().apply {
            dataSource = dataSource()
            setPackagesToScan("spring.jpa")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
        }

    @Bean
    open fun transactionManager(
        entityManagerFactory: EntityManagerFactory): PlatformTransactionManager =
        JpaTransactionManager(entityManagerFactory)
}

val applicationContext =
    AnnotationConfigApplicationContext(JpaConfig::class.java)

fun main() {
    applicationContext.beanDefinitionNames.forEachIndexed { i, name ->
        println("$i: $name")
    }
}