package hello.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

/**
 * 인터페이스 없을 떄
 */
@Slf4j
public class ConcreteService {
    public void call() {
        log.info("ConcreteService 호출");
    }
}
