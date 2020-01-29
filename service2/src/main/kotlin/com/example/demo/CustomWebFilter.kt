package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class CustomWebFilter: WebFilter {
    companion object {
        private val log = LoggerFactory.getLogger(CustomWebFilter::class.java)
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        log.info("${exchange.request.headers}")
        return chain.filter(exchange)
    }
}
