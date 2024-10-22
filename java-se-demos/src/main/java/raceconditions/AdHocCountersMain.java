package raceconditions;

import annotations.BadPractice;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@BadPractice("Don't use low level syncronization, use higer level, for example syncronizers, Lock, etc.")
@Slf4j
public class AdHocCountersMain {

    @Getter
    private int counter;

    private Object lock = new Object();

    public void increment() {
        log.debug("Incrementing counter");
        synchronized (lock) {
            counter++;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        AdHocCountersMain counterMain = new AdHocCountersMain();
        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                counterMain.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();

        Thread.sleep(Duration.ofSeconds(1));
        log.debug("Counter: {}", counterMain.getCounter());
    }
}
