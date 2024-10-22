package scheduled;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledMain {

    @SneakyThrows
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        service.scheduleAtFixedRate(
                () -> log.debug("Hello scheduling"),
                0,
                1, TimeUnit.SECONDS
        );

        Thread.sleep(Duration.ofSeconds(5));
        service.shutdown();

    }
}
