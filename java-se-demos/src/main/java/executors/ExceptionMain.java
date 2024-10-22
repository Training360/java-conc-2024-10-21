package executors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class ExceptionMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<?> future = executorService.submit(() -> {
            throw new IllegalStateException("Something went wrong");
                }
                );

        try {
            Object o = future.get();
            log.debug("Result: {}", o);
        }catch (InterruptedException | ExecutionException e) {
            log.error("Error", e);
        }

        executorService.shutdown();
    }
}
