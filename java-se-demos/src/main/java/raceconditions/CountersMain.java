package raceconditions;

import annotations.BadPractice;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@BadPractice("Race condition typical sample!")
@Slf4j
public class CountersMain {

    @Getter
    private int counter;

    public void increment() {
//        counter++; // critical section
        //counter = counter + 1
        int temp = counter;
        temp = temp + 1;
        counter = temp;
    }

    @SneakyThrows
    public static void main(String[] args) {
        CountersMain counterMain = new CountersMain();
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
