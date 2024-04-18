package spring.jpa

import jakarta.persistence.EntityManagerFactory
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy
import org.mariadb.jdbc.MariaDbDataSource
import org.springframework.context.annotation.*
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@ComponentScan
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
            // 엔티티의 프라퍼티와 테이블의 컬럼을 매핑하는 방법
            jpaPropertyMap["hibernate.physical_naming_strategy"] =
                CamelCaseToUnderscoresNamingStrategy::class.java

        }

    @Bean
    open fun transactionManager(
        entityManagerFactory: EntityManagerFactory): PlatformTransactionManager =
        JpaTransactionManager(entityManagerFactory)
}

val applicationContext =
    AnnotationConfigApplicationContext(JpaConfig::class.java)  //app실행될 때 모든 Jpa bean들 로드 후 관리

fun main() {
    applicationContext.beanDefinitionNames.forEachIndexed { i, name ->  //Spring에 있는 모든 Bean들 정보 가져와
                                                                        // 각각 이름, 인덱스 출력
        println("$i: $name")
    }
}