package tasks;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class SchedulerInitializer {

    private static final Logger log = LoggerFactory.getLogger(SchedulerInitializer.class);

    @Resource(name="concurrent/_defaultManagedScheduledExecutorService")
    private ManagedScheduledExecutorService managedScheduledExecutorService;

    private ScheduledFuture<?> scheduledFuture;

    public void startScheduledTask(@Observes @Initialized(ApplicationScoped.class) Object init) {
        scheduledFuture = managedScheduledExecutorService.scheduleAtFixedRate(
                () -> log.info("Hello Scheduled Jakarta EE"),
                0,
                1, TimeUnit.SECONDS
        );
    }

    public void stopScheduledTask(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        scheduledFuture.cancel(true);
    }
}
