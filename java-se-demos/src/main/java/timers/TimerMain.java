package timers;

import annotations.BadPractice;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

@BadPractice("Use ScheduledThreadPoolExecutor instead!")
@Slf4j
public class TimerMain {

    @SneakyThrows
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {

            private int counter = 0;

            @Override
            public void run() {
                log.debug("Sending e-mail messages");
                counter++;
                if (counter == 3) {
                    throw new IllegalArgumentException("Too much");
                }
            }
        };

        Timer timer = new Timer();

        timer.schedule(task, Duration.ofSeconds(2).toMillis(),
                Duration.ofSeconds(2).toMillis());

//        timer.scheduleAtFixedRate(task, Duration.ofSeconds(2).toMillis(),
//                Duration.ofSeconds(2).toMillis());

        Thread.sleep(Duration.ofSeconds(10));

        timer.cancel();

        log.debug("End");
    }
}
