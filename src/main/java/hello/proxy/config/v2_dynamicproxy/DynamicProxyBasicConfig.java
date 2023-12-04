package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.V1.OrderControllerV1;
import hello.proxy.app.V1.OrderControllerV1Impl;
import hello.proxy.app.V1.OrderRepositoryV1;
import hello.proxy.app.V1.OrderRepositoryV1Impl;
import hello.proxy.app.V1.OrderServiceV1;
import hello.proxy.app.V1.OrderServiceV1Impl;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderControllerV1 orderControllerV1(final LogTrace logTrace) {
        final OrderControllerV1 orderController = new OrderControllerV1Impl(
            orderServiceV1(logTrace));

        final OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(
            OrderControllerV1.class.getClassLoader(),
            new Class[]{OrderControllerV1.class},
            new LogTraceBasicHandler(orderController, logTrace));
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(final LogTrace logTrace) {
        final OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

        final OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(
            OrderServiceV1.class.getClassLoader(),
            new Class[]{OrderServiceV1.class},
            new LogTraceBasicHandler(orderService, logTrace));

        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(final LogTrace logTrace) {
        final OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

        final OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(
            OrderRepositoryV1.class.getClassLoader(),
            new Class[]{OrderRepositoryV1.class},
            new LogTraceBasicHandler(orderRepository, logTrace)
        );

        return proxy;
    }
}
