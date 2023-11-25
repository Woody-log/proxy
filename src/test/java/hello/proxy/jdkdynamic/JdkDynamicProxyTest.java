package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.jdkdynamic.code.AInterface;
import hello.proxy.jdkdynamic.code.BImlp;
import hello.proxy.jdkdynamic.code.BInterface;
import hello.proxy.jdkdynamic.code.TimeInvocationHandler;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        final AInterface target = new AImpl();
        final TimeInvocationHandler handler = new TimeInvocationHandler(target);

        final AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
            new Class[]{AInterface.class}, handler);

        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        final BInterface target = new BImlp();
        final TimeInvocationHandler handler = new TimeInvocationHandler(target);

        final BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(),
            new Class[]{BInterface.class}, handler);

        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
}
