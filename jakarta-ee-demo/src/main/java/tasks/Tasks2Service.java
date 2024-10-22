package tasks;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@ApplicationScoped
public class Tasks2Service {

    private static final Logger log = LoggerFactory.getLogger(Tasks2Service.class);

//    @Resource
//    private UserTransaction userTransaction;

    @Resource(name = "concurrent/_defaultManagedExecutorService")
    private ManagedExecutorService managedExecutorService;

    public void startCount() {
        managedExecutorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                log.info("Count: {}", i);
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                }
                catch (InterruptedException e) {
                    log.error("Interrupted", e);
                }
            }
        });
    }
}
