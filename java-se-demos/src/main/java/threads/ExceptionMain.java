package threads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class ExceptionMain {

    @SneakyThrows
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @SneakyThrows
            public void run() {
//                try {
                    Thread.sleep(Duration.ofSeconds(2));
                    throw new IllegalStateException("Testing exception in thread");
//                } catch (IllegalStateException e) {
//                    log.error("Exception in thread", e);
//                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.setUncaughtExceptionHandler((t, e) -> log.error("Exception handler", e));
        thread.start();

        Thread.sleep(Duration.ofSeconds(5));
    }
}
