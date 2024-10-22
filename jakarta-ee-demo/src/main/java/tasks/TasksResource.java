package tasks;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Path("/tasks")
public class TasksResource {

    private static final Logger log = LoggerFactory.getLogger(TasksResource.class);

//    @Inject
//    private TasksService tasksService;

    @Inject
    private Tasks2Service tasks2Service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTask() {
        log.info("POST method called");

//        Future<Integer> future = tasksService.startCount();

//        return Response.status(Response.Status.ACCEPTED).build();

//        try {
//            int response = future.get();
//            return Response.ok(new CounterResult(response)).build();
//        }catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException("Something went wrong", e);
//        }
        tasks2Service.startCount();
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
