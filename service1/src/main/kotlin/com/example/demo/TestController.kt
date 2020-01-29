package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class TestController(private val testService: TestService) {
    companion object {
        private val log = LoggerFactory.getLogger(TestController::class.java)
    }

    @GetMapping("/test")
    fun test(): Mono<String> {
        Thread.sleep(3000)
        log.info("Get /test")
        return testService.test()
                .map { "$it ${Date()}" }
    }
}
