package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * v1: 인터페이스와 구현 클래스 - 스프링 빈으로 수동 등록
 */
@RequestMapping//스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식
@ResponseBody
public interface OrderControllerV1 {
    /**
     * interface에는 @RequestParam 생략시 자바 버전에 따라 인식 못할 수도 있음! - interface <- @RequestParam 필수!
     */
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
