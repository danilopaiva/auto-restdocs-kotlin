package br.com.zup.realwave.sponsorship.repository.config

import br.com.zup.realwave.sponsorship.repository.liquibase.LiquibaseConfig
import br.com.zup.spring.tenant.TenantConfig
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@Import(LiquibaseConfig::class, TenantConfig::class)
@ComponentScan(basePackages = ["br.com.zup.realwave.sponsorship.repository"])
open class RepositoryConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    open fun dataSource() =
            DataSourceBuilder.create().build()!!

    @Bean
    open fun jdbcTemplate(dataSource: DataSource) =
            JdbcTemplate(dataSource)

    @Bean
    open fun namedJdbcTemplate(jdbcTemplate: JdbcTemplate) =
            NamedParameterJdbcTemplate(jdbcTemplate)

    @Bean
    open fun txManager(): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource())
    }

}

