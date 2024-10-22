package tasks;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.UUID;

@Path("/events")
public class SseResource {

    private static final Logger log = LoggerFactory.getLogger(SseResource.class);

    private OutboundSseEvent.Builder eventBuilder;

    @Context
    public void setSse(final Sse sse) {
        eventBuilder = sse.newEventBuilder();
    }

    @Resource(name = "concurrent/_defaultManagedExecutorService")
    ManagedExecutorService managedExecutorService;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void sendEvents(@Context SseEventSink sink) {
        managedExecutorService.submit(() -> {
           for (int i = 0; i < 10; i++) {
               log.info("Count: {}", i);
               sink.send(eventBuilder
                               .data(new CounterResult(i))
                               .id(UUID.randomUUID().toString())
                               .mediaType(MediaType.APPLICATION_JSON_TYPE)
                               .comment("This is the counter")
                       .build());
               try {
                   Thread.sleep(Duration.ofSeconds(1));
               }
               catch (InterruptedException e) {
                   log.error("Interrupted", e);
               }
           }
           sink.close();
        });
    }
}
