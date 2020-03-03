package com.example.demo

import com.example.demo.protobuf.Hello
import com.example.demo.protobuf.HelloServiceGrpc
import com.example.demo.protobuf.ReactorHelloServiceGrpc
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono
import java.util.*

@GRpcService
//class HelloService: HelloServiceGrpc.HelloServiceImplBase() {
//    companion object {
//        private val log = LoggerFactory.getLogger(HelloGrpcService::class.java)
//    }
//
//    override fun hello(request: Hello.MessageRequest, responseObserver: StreamObserver<Hello.MessageResponse>) {
//        log.info("rpc hello")
//
//        val message = request.message + " " + Date()
//        val response = Hello.MessageResponse.newBuilder().setMessage(message).build()
//        responseObserver.onNext(response)
//        responseObserver.onCompleted()
//    }
//}

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
