package threads;

import annotations.BadPractice;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@BadPractice("Don't extends Thread, use Runnable instead!")
public class WorstPracticeMain {

    public static void main(String[] args) {
        CounterThread counterThread = new CounterThread();
        // Sose hívjuk meg a run() metódust, az nem hoz létre új szálat
        counterThread.start();
    }

    // Kerülendő
    @Slf4j
    private static class CounterThread extends Thread {
        @Override
        @SneakyThrows
        public void run() {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(Duration.ofSeconds(1));
                log.debug("Counter: {}", i);
            }
        }
    }
}
