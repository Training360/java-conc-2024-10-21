package threadlocals;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class LogService {

    private final ThreadLocal<String> requestId;

    public void log() {
        log.debug("Log: {}", requestId.get());
    }
}
