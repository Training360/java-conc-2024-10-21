package raceconditions;

import annotations.BadPractice;

@BadPractice("Don't use static methods, locks!")
public class StaticSyncronizedMain {

    public void m() {
        synchronized (SyncCountersMain.class) {

        }
    }

    public static void main(String[] args) {

    }
}
