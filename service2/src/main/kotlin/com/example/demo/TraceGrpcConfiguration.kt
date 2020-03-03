package com.example.demo

import brave.Tracing
import brave.grpc.GrpcTracing
import io.grpc.ClientInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TraceGrpcConfiguration {
    @Bean
    fun grpcTracing(tracing: Tracing?): GrpcTracing {
        return GrpcTracing.create(tracing)
    }

    @Bean
    fun grpcClientSleuthInterceptor(grpcTracing: GrpcTracing): ClientInterceptor {
        return grpcTracing.newClientInterceptor()
    }
}
