package raceconditions;

import annotations.BadPractice;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@BadPractice("""
        * Don't use synchronized
        * Don't use Collections.synchronizedList
        * Don't use thread.sleep for waiting!
        """)
@Slf4j
public class CheckThanActMain {

    @Getter
//    private List<Integer> numbers = new ArrayList<>();

    private List<Integer> numbers = Collections.synchronizedList(new ArrayList<>());

    public synchronized void addIfAbsent(int number) {
        if (!numbers.contains(number)) {
            numbers.add(number);
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        CheckThanActMain checkThanActMain = new CheckThanActMain();

        Runnable task = () -> {
          for (int i = 0; i < 10_000; i++) {
              checkThanActMain.addIfAbsent(i);
          }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        Thread.sleep(Duration.ofSeconds(1));

        log.debug("Numbers: {}", checkThanActMain.getNumbers().size());
    }
}
