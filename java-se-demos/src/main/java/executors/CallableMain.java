package executors;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.*;

@Slf4j
public class CallableMain {

    @SneakyThrows
    int sum() {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            log.debug("i: {}", i);
            sum += i;
            Thread.sleep(Duration.ofMillis(50));
        }
        return sum;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CallableMain callableMain = new CallableMain();
        Future<Integer> result = executorService.submit(callableMain::sum);
        log.debug("Result: {}", result.isDone());
        log.debug("After submit");

//        try {
//            Integer sum = result.get(1, TimeUnit.SECONDS);
//            log.debug("Sum: {}", sum);
//        }
//        catch (InterruptedException | ExecutionException | TimeoutException e) {
//            log.debug("Something went wrong", e);
//        }


        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);

            log.debug("Done: {}", result.isDone());
            log.debug("Value: {}", result.get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Interrupted", e);
        }

        log.debug("End");
    }
}
