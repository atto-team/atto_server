package com.atto.nimontoy

import com.atto.nimontoy.jpa.DataSourceProperties
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 * Created by 00700mm@gmail.com on 2019-07-31
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(DataSourceProperties::class)
class JpaConfig(
        private val dataSourceProperties: DataSourceProperties
) {

    // 서비스에 @Transactional 만 붙이면 자동으로 트랜잭션 관리 해줌.
    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }

    @Bean
    fun dataSource(): DataSource =
            HikariDataSource().let {
                it.jdbcUrl = dataSourceProperties.url +
                        dataSourceProperties.db +
                        dataSourceProperties.options
                it.username = dataSourceProperties.username
                it.password = dataSourceProperties.password
                it
            }


    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val factoryBean = LocalContainerEntityManagerFactoryBean()
        factoryBean.setPackagesToScan("com.atto.nimontoy")
        factoryBean.jpaVendorAdapter = this.jpaVendorAdapter()
        factoryBean.persistenceUnitName = "stadto"
        factoryBean.dataSource = dataSource()

        val properties = Properties()
        properties.setProperty("hibernate.format_sql", "true")
        properties.setProperty("hibernate.hibernate.temp.use_jdbc_metadata_defaults", "false")
        properties.setProperty("hibernate.hbm2ddl.auto", "update")

        factoryBean.setJpaProperties(properties)

        return factoryBean
    }

    @Bean
    fun jpaVendorAdapter(): HibernateJpaVendorAdapter {
        val hibernateJpaVendorAdapter = HibernateJpaVendorAdapter()
        hibernateJpaVendorAdapter.setShowSql(true)
        hibernateJpaVendorAdapter.setGenerateDdl(false)
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect")
        return hibernateJpaVendorAdapter
    }

}
