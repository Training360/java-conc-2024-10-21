package threadlocals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class LogMain {

    private static final Logger log = LoggerFactory.getLogger(LogMain.class);

    public static void main(String[] args) {
        ThreadLocal<String> requestId = new ThreadLocal<>();
        LogService logService = new LogService(requestId);
//        Runnable logger = logService::log;

        Runnable logger = () -> {
            requestId.set(UUID.randomUUID().toString());
            logService.log();
        };

        for (int i = 0; i < 5; i++) {
            new Thread(logger).start();
        }
    }
}
