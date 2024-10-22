package liveness;

import annotations.BadPractice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@BadPractice("Deadlock")
@AllArgsConstructor
@Slf4j
public class Friend {

    @Getter
    private String name;

    public synchronized void bow(Friend friend) {
        log.debug("{}: {} has bowed to me!", name, friend.getName());
        friend.bowBack(this);
    }

    private synchronized void bowBack(Friend friend) {
        log.debug("{}: {} has bowed back!", name, friend.getName());
    }

    public static void main(String[] args) {
        Friend john = new Friend("John");
        Friend jane = new Friend("Jane");
        Thread thread1 = new Thread(() -> john.bow(jane));
        Thread thread2 = new Thread(() -> jane.bow(john));
        thread1.start();
        thread2.start();
    }


}
