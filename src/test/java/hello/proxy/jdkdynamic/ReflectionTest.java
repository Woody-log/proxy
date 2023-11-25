package hello.proxy.jdkdynamic;


import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {

        final Hello target = new Hello();

        // 공통 로직1 시작
        log.info("start");
        final var result1 = target.callA();
        log.info("result={}", result1);

        // 공통 로직2 시작
        log.info("start");
        final var result2 = target.callB();
        log.info("result={}", result2);
    }

    @Test
    void reflection1() throws Exception {
        // 클래스 정보
        final Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        final Hello target = new Hello();

        // callA 메서드 정보
        final var methodCallA = classHello.getMethod("callA");
        final var result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);
        // 공통로직1 종료

        // callB 메서드 정보
        final var methodCallB = classHello.getMethod("callB");
        final var result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
        // 공통로직2 종료
    }

    @Test
    void reflection2() throws Exception {
        // 클래스 정보
        final Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        final Hello target = new Hello();
        final var methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        final var methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(final Method mehtod, final Object target) throws Exception {
        log.info("start");
        final var result = mehtod.invoke(target);
        log.info("result={}", result);

    }

    @Slf4j
    static class Hello {

        public String callA() {
            log.info("call A");
            return "A";
        }

        public String callB() {
            log.info("call B");
            return "B";
        }

    }

}


