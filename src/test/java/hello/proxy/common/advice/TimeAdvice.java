package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 스프링이 제공하는 프록시 생성: ProxyFactory
 * 부가 기능을 적용하기 위해 Logic 넣는 곳: Advice
 */
@Slf4j
public class TimeAdvice implements MethodInterceptor {
    /**
     * ProxyFactory 생성 할 때 target을 들고 있기 때문에 target 없어도 됨!
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = invocation.proceed(); //target을 찾고 인수를 넘기고 Logic 실행 해줌!

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
