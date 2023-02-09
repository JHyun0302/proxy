package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {
    private final OrderServiceV2 target;
    private final LogTrace logTrace;

    /**
     * 자식 클래스를 생성할 때는 항상 super()로 부모 클래스의 생성자를 호출해야 함! 이 부분 생략하면 기본 생성자 호출!
     * OrderServiceV2: 기본 생성자가 없어서 super 필요
     * 기본 생성자 없는 이유: private final이라 생성자 1개만 가능해서...
     * 프록시는 부모 객체의 기능 사용 안하므로 null 입력 가능
     */
    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.orderItem()");
            //target 호출
            target.orderItem(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
