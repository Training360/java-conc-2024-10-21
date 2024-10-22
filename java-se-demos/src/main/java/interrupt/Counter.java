package interrupt;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class Counter {

//    private volatile boolean shouldCount = true;

    public void increment() {
        int i = 0;
//        while (shouldCount) {
        while (!Thread.currentThread().isInterrupted()) {
            i++;
            log.debug("Counter: {}", i);
            try {
                Thread.sleep(Duration.ofSeconds(10));
            }
            catch (InterruptedException e) {
                log.debug("Interrupted");
//                break;
                Thread.currentThread().interrupt(); // öninterrupt
            }
        }
        log.debug("While stopped");
    }

//    public void stop() {
//        shouldCount = false;
//    }

    @SneakyThrows
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread thread = new Thread(counter::increment);
        thread.start();

        Thread.sleep(Duration.ofSeconds(5));
//        counter.stop();

        thread.interrupt();
        log.debug("End");
    }
}
