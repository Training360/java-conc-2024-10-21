package tasks;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.Asynchronous;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@ApplicationScoped
public class TasksService {

    private static final Logger log = LoggerFactory.getLogger(TasksService.class);


        @Asynchronous
//        @Transactional
    public CompletableFuture<Integer> startCount() {
        for (int i = 0; i < 10; i++) {
            log.info("Count: {}", i);
            try {
                Thread.sleep(Duration.ofSeconds(1));
            }
            catch (InterruptedException e) {
                log.error("Interrupted", e);
            }
        }
        return CompletableFuture.completedFuture(42);
    }
}
