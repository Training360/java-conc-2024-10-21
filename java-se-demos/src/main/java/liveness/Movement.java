package liveness;

import annotations.BadPractice;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@BadPractice("Livelock sample")
@AllArgsConstructor
@Slf4j
public class Movement implements Runnable {

    private Character first;

    private Character second;

    @Override
    public void run() {
        while (true) {
            if (first.isMoveAllowed()) {
                log.debug("{} karakter mozogni próbál", first);
                first.disallowMove();
                second.allowMove();
            }
        }
    }
}
