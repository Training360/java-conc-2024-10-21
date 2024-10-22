package raceconditions;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class JoinMain {

    @SneakyThrows
    public void count() {
        for (int i = 0; i < 10; i++) {
            log.debug("Counter: {}", i);
            Thread.sleep(Duration.ofSeconds(5));
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        JoinMain joinMain = new JoinMain();
        Runnable task = joinMain::count;

        Thread thread = new Thread(task);
        thread.setName("Counter-Thread");
        thread.start();

        // Timeout nélkül:
//        thread.join();
        // Timeouttal:
        thread.join(Duration.ofSeconds(2));

        log.debug("End");
    }
}
