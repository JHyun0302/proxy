package hello.proxy.pureproxy.proxy.code;

/**
 * 캐시 적용 전: 1번 호출 -> 1초
 */
public class ProxyPatternClient {
    private Subject subject;

    public ProxyPatternClient(Subject subject) {
        this.subject = subject;
    }

    public void execute() {
        subject.operation();
    }
}
