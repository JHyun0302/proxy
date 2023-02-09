package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {
    /**
     * 실행순서
     * proxy.call() -> Interface: InvocationHandler의 invoke & 구현체: TimeInvocationHandler의 invoke (method.invoke() 써서 추상화(동적으로 처리 가능))
     * method.invoke(target, args) 실행-> target = 실제 객체(AImpl, BImpl) 호출
     * 그리고 종료
     */
    @Test
    void dynamicA() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        /**
         * 프록시 생성: Proxy.newProxyInstance(어느 클래스를 할지, 어떤 인터페이스 기반인지, 프록시가 사용하는 로직)
         */
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        /**
         * 프록시 생성: Proxy.newProxyInstance(어느 클래스를 할지, 어떤 인터페이스 기반인지, 프록시가 사용하는 로직)
         */
        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);
        proxy.call(); //AInterface의 call()
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    /**
     * <정리>
     * 프록시 자동 생성
     * timeInvocationHandler로 로직을 정의한다!
     * </정리>
     */
}
