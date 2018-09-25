package com.github.danilopaiva.bank.repository.config

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = ["com.github.danilopaiva.bank.repository"])
open class RepositoryConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    open fun firstDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    open fun firstDataSource(): DataSource {
        return firstDataSourceProperties().initializeDataSourceBuilder().build()
    }

    @Bean
    open fun jdbcTemplate(dataSource: DataSource) =
        JdbcTemplate(dataSource)

}

