package raceconditions;

import annotations.BadPractice;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@BadPractice("Don't use low level syncronization, use higer level, for example syncronizers, Lock, etc.")
@Slf4j
public class SyncCountersMain {

    @Getter
    private int counter;

//    public synchronized void increment() {
        // elkéri a this objektumhoz tartozó lockot
//        counter++; // critical section
        //counter = counter + 1
//        int temp = counter;
//        temp = temp + 1;
//        counter = temp;
        // visszaadja a this objektumhoz tartozó lockot
//    }

    public void increment() {
        log.debug("Incrementing counter");
        synchronized (this) {
            counter++;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        SyncCountersMain counterMain = new SyncCountersMain();
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
