package com.example.demo

import brave.Tracing
import brave.grpc.GrpcTracing
import io.grpc.ServerInterceptor
import org.lognet.springboot.grpc.GRpcGlobalInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TraceGrpcConfiguration {
    @Bean
    fun grpcTracing(tracing: Tracing?): GrpcTracing {
        return GrpcTracing.create(tracing)
    }

    @Bean
    @GRpcGlobalInterceptor
    fun grpcServerInterceptor(grpcTracing: GrpcTracing): ServerInterceptor {
        return grpcTracing.newServerInterceptor()
    }
}
