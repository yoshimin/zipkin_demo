package com.example.demo

import com.example.demo.protobuf.Hello
import com.example.demo.protobuf.ReactorHelloServiceGrpc
import io.grpc.ClientInterceptor
import io.grpc.ManagedChannelBuilder
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TestController(private val interceptors: List<ClientInterceptor>) {
    companion object {
        private val log = LoggerFactory.getLogger(TestController::class.java)
    }

    @GetMapping("/test")
    fun test(): Mono<String> {
        log.info("Get /test")
        return Mono.just("test")
    }

    @GetMapping("/grpc/test")
    fun grpcTest(): Mono<String> {
        log.info("Get /grpc/test")

        val channel = ManagedChannelBuilder.forAddress("localhost", 6567)
                .usePlaintext()
                .intercept(interceptors)
                .build()
        val request = Hello.MessageRequest.newBuilder()
                .setMessage("hello")
                .build()
//        val stub = HelloServiceGrpc.newFutureStub(channel)
//        val response = stub.hello(request)
//
//        return Mono.create {
//            Futures.addCallback(
//                    response,
//                    object : FutureCallback<Hello.MessageResponse> {
//                        override fun onFailure(t: Throwable) {
//                            it.error(t)
//                        }
//                        override fun onSuccess(result: Hello.MessageResponse?) {
//                            it.success(result?.message)
//                        }
//                    },
//                    Executors.newCachedThreadPool()
//            )
//        }

        var stub = ReactorHelloServiceGrpc.newReactorStub(channel)
        return stub.hello(request).map { it.message }
    }
}
