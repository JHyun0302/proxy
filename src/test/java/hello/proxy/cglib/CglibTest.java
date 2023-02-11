package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {
    /**
     * CGLIB 적용(인터페이스 없는 경우)
     * CGLIB 런타임 객체 의존 관계
     * client   --->   cglib proxy   --->   TimeMethodInterceptor   --->   target
     *         call()             interceptor()             methodProxy.invoke-call()
     */

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer(); //Enhancer 이용해서 프록시 생성 가능
        enhancer.setSuperclass(ConcreteService.class); //만드는 프록시 부모 클래스: ConcreteService - 상속!!!
        enhancer.setCallback(new TimeMethodInterceptor(target)); //프록시에 작용할 실행 로직 할당
        ConcreteService proxy = (ConcreteService) enhancer.create(); //프록시 create
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}
