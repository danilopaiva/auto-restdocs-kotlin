package com.github.danilopaiva.bank.repository.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@Configuration
@Import(RepositoryConfig::class)
@ComponentScan(basePackages = ["com.github.danilopaiva.bank.repository"])
@ActiveProfiles(profiles = ["test"])
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
open class RepositoryTestConfig