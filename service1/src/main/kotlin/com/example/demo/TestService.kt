package com.example.demo

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Service
class TestService(private val webClient: WebClient) {
    fun test(): Mono<String> {
        return webClient
                .get()
                .uri("http://localhost:8081/grpc/test")
                .retrieve()
                .bodyToMono()
    }
}
