package com.github.danilopaiva.bank.web.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = ["com.github.danilopaiva.bank"])
open class ApplicationConfig
