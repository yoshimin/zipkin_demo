package com.example.demo

import com.example.demo.protobuf.Hello
import com.example.demo.protobuf.ReactorHelloServiceGrpc
import org.lognet.springboot.grpc.GRpcService
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono
import java.util.*

@GRpcService
class HelloService: ReactorHelloServiceGrpc.HelloServiceImplBase() {
    companion object {
        private val log = LoggerFactory.getLogger(HelloService::class.java)
    }

    override fun hello(request: Mono<Hello.MessageRequest>): Mono<Hello.MessageResponse> {
        log.info("rpc hello")
        return request.map {
            log.info("massage ${it.message}")
            val message = it.message + " " + Date()
            Hello.MessageResponse.newBuilder().setMessage(message).build()
        }
    }
}
