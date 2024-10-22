package liveness;

public class Character {

    private boolean moveAllowed = false;

    public synchronized void allowMove() {
        moveAllowed = true;
    }

    public synchronized void disallowMove() {
        moveAllowed = false;
    }

    public synchronized boolean isMoveAllowed() {
        return moveAllowed;
    }

    public static void main(String[] args) {
        Character character1 = new Character();
        Character character2 = new Character();

        Thread thread1 = new Thread(new Movement(character1, character2));
        Thread thread2 = new Thread(new Movement(character2, character1));

        character1.allowMove();

        thread1.start();
        thread2.start();
    }
}


