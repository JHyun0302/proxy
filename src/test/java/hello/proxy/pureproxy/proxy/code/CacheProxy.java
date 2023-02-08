package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 캐시 적용: 변하지 않는 데이터는 캐시에 저장 & 조회
 */
@Slf4j
public class CacheProxy implements Subject{
    private Subject target;
    private String cacheValue;

    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("프록시 호출");
        if (cacheValue == null) { // 첫 호출시 실제 객체(target) operation() 실행
            cacheValue = target.operation();
        }
        return cacheValue; // 2번째부터는 실제 객체 실행 안함!
    }
}
