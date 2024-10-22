package raceconditions;

public class ReentrantMain {

    public synchronized void a() {
        b();
    }

    public synchronized void b() {

    }

    public static void main(String[] args) {
        new ReentrantMain().a();
    }
}
