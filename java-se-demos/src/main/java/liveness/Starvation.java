package liveness;

import annotations.BadPractice;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@BadPractice("Can not demo starvation")
@Slf4j
public class Starvation {

    @SneakyThrows
    private void waitFor(long time) {

        while (true) {
            log.debug("Waiting");
            synchronized (this) {
                Thread.sleep(time);
            }
//            Thread.yield();
        }
    }

    public static void main(String[] args) {
        Starvation starvation = new Starvation();
        Thread thread1 = new Thread(() -> starvation.waitFor(50), "p10-1");
        Thread thread2 = new Thread(() -> starvation.waitFor(50), "p10-2");
        Thread thread3 = new Thread(() -> starvation.waitFor(50), "p10-3");
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread3.setPriority(Thread.MAX_PRIORITY);

        Thread lowPriority = new Thread(() -> starvation.waitFor(50), "p1-1");
        lowPriority.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();
        lowPriority.start();
    }
}
