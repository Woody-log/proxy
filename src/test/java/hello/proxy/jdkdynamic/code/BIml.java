package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BIml implements BInterface {
    @Override
    public String call() {
        log.info("B호출");
        return "B";
    }
}
