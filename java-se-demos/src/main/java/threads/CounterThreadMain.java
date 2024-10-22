package threads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class CounterThreadMain {

    public static void main(String[] args) {
        Runnable counter = () -> {
            try {
                Thread.sleep(Duration.ofSeconds(5));
                log.debug("Hello Thread");
                for (int i = 0; i < 10; i++) {
                    log.debug("Counter: {}", i);
                    Thread.sleep(Duration.ofSeconds(1));
                }
            }catch (InterruptedException e) {
                log.error("Interrupted", e);
            }
        };

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(counter);
            thread.start();
        }

        log.debug("End");
    }
}
