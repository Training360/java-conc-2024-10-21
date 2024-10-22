package raceconditions;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class GuardedBlockMain {

    private boolean condition = false;

    @SneakyThrows
    public synchronized void waitForCondition() {
        while (!condition) {
            log.debug("Waiting for signal");
            wait();
            log.debug("Got signal");
        }
    }

    public synchronized void signal() {
        condition = true;
        notify();
    }

    @SneakyThrows
    public static void main(String[] args) {
        GuardedBlockMain guardedBlockMain = new GuardedBlockMain();

        Runnable task = guardedBlockMain::waitForCondition;
        Thread thread = new Thread(task);
        thread.start();

        Thread.sleep(Duration.ofSeconds(2));
        guardedBlockMain.signal();

        log.debug("End");
    }
}
