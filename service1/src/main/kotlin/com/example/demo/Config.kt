package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class Config {
    @Bean
    fun webclient(): WebClient {
        return WebClient.builder().build()
    }
}
