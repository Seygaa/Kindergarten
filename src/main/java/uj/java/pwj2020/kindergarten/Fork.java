package uj.java.pwj2020.kindergarten;

import java.util.concurrent.Semaphore;

class Fork {
    private final Semaphore fork = new Semaphore(1);

    public void take() {
        try {
            fork.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void putDown() {
        fork.release();
    }
}
