package raceconditions;

import annotations.BadPractice;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@BadPractice("Use synchronized collection from Class Library!")
@Slf4j
public class CollectionsMain {

    @Getter
    private List<Integer> numbers = new ArrayList<>();

    public synchronized void add(Integer number) {
        numbers.add(number);
    }

    @SneakyThrows
    public static void main(String[] args) {
        CollectionsMain collectionsMain = new CollectionsMain();
        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                collectionsMain.add(1);
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        Thread.sleep(Duration.ofSeconds(1));

        log.debug("Number of elements: {}", collectionsMain.getNumbers().size());
    }
}
