package executors;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ExecutorsMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                log.info("Counter: {}", i);
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                } catch (InterruptedException e) {
                    log.error("Interrupted", e);
                }
            }
        };
        executorService.submit(task);
        executorService.submit(task);
        executorService.submit(task);
        executorService.shutdown();
    }
}
