package atomics;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Counter {

    @Getter
    private AtomicInteger value = new AtomicInteger(0);

    private void increment() {
        value.incrementAndGet();
    }

    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Counter counter = new Counter();
        Runnable task = () -> {
                for (int i = 0; i < 10_000; i++) {
                    counter.increment();
                }
        };
        service.submit(task);
        service.submit(task);

        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);

        log.debug("Value: {}", counter.getValue());
    }
}
