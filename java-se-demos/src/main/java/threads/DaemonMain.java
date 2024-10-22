package threads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class DaemonMain {

    @SneakyThrows
    public static void main(String[] args) {
        Runnable counter = new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                for (int i = 0; i < 10; i++) {
                    log.debug("Counter: {}", i);
                    Thread.sleep(Duration.ofSeconds(1));
                }
            }
        };

        Thread thread = new Thread(counter);

        thread.setDaemon(true);
        thread.start();
        Thread.sleep(Duration.ofSeconds(2));
        thread.join();
        log.debug("End");
    }
}
