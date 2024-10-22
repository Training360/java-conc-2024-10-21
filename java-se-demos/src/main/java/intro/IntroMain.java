package intro;

import annotations.BadPractice;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class IntroMain {

    public static void main(String[] args) {
        log.debug("Hello World!");
        log.info("Hello!");

        int minute = 60 * 1000;
        log.info("Minute: {}", minute);

        long fiveMinutesInMilliseconds = Duration.ofMinutes(5).toMillis();
        log.info("FiveMinutesInMilliseconds: " + fiveMinutesInMilliseconds);
    }
}
