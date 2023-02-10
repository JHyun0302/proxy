package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

/**
 * 동적 프록시 app에 적용
 * 런타임 객체 의존 관계
 * client -> orderControllerProxy (logTraceBasicHandler 동적으로 생성함) -> orderControllerV1Impl
 *        -> orderServiceProxy (logTraceBasicHandler 동적으로 생성함) -> orderServiceV1Impl
 *        -> orderRepositoryProxy (logTraceBasicHandler 동적으로 생성함) -> orderRepositoryV1Impl
 */
@Configuration
public class DynamicProxyBasicConfig {
    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1Impl orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));

        //proxy 적용
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceBasicHandler(orderController, logTrace));
        return proxy;
    }
    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1Impl orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

        //proxy 적용
        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceBasicHandler(orderService, logTrace));
        return proxy;
    }
    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1Impl orderRepository = new OrderRepositoryV1Impl();

        //proxy 적용
        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceBasicHandler(orderRepository, logTrace));
        return proxy;
    }
}
