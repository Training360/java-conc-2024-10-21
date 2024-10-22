package threads;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class ThreadsMain {

    public static void main(String[] args) {
        Thread current = Thread.currentThread();
        log.debug("Current thread: {}, {}", current, current.getName());

        StackTraceElement[] elements = current.getStackTrace();
        for (StackTraceElement element : elements) {
            log.debug("Element: {}", element);
        }

        try {
            Thread.sleep(Duration.ofSeconds(30));
        }
        catch (InterruptedException e) {
            log.error("Interrupted", e);
        }
    }
}
