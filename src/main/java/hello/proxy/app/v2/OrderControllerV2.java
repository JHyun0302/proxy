package hello.proxy.app.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * v2: 인터페이스 없는 구체 클래스 - 스프링 빈으로 수동 등록
 */

/**
 * 스프링 MVC: @Controller or @RequestMapping 있어야 스프링 컨트롤러로 인식!
 * @Controller: 자동 컴포넌트 스캔 대상
 * @RequestMapping: 수동 빈 등록
 */
@RequestMapping
@ResponseBody
@Slf4j
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    public OrderControllerV2(OrderServiceV2 orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/v2/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v2/no-log")
    public String noLog() {
        return "ok";
    }
}
