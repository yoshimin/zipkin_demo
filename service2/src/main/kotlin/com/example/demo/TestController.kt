package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TestController {
    companion object {
        private val log = LoggerFactory.getLogger(TestController::class.java)
    }

    @GetMapping("/test")
    fun test(): Mono<String> {
        log.info("Get /test")
        return Mono.just("test")
    }
}
