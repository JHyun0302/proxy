package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    /**
     * 리플렉션 적용 전
     */
    @Test
    void reflection() {
        Hello target = new Hello();

        //공통 로직1 시작
        log.info("start");
        String result1 = target.callA(); //호출하는 메서드가 다름
        log.info("result={}",result1);
        //공통 로직1 종료

        //공통 로직2 시작
        log.info("start");
        String result2 = target.callB(); //호출하는 메서드가 다름
        log.info("result={}",result2);
        //공통 로직2 종료
    }

    @Test
    void reflection1() throws Exception {
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        /**
         * .getMethod()를 사용해서 Method로 대체됨!
         */
        Hello target = new Hello();
        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA"); //call 메서드 메타정보
        Object result1 = methodCallA.invoke(target); //실제 인스턴스의 메서드 호출
        log.info("result1={}", result1);

        //callA 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallA.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        /**
         * 리플렉션 적용 후
         * 클래스의 메타 정보 & 인터턴스 객체의 메타정보를 바탕으로 추상화 & 공통으로 묶어서 처리!
         */
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA"); //call 메서드 메타정보
        dynamicCall(methodCallA, target);

        //callA 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    /**
     * method.invoke() 써서 추상화(동적으로 처리 가능)
     */
    private void dynamicCall(Method method, Object target) throws Exception{
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}",result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
