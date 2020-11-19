package uj.java.pwj2020.kindergarten;

public class Preschooler extends Child implements Runnable {
    private final Fork leftFork;
    private final Fork rightFork;

    public Preschooler(ChildDescription childDescription, Fork leftFork, Fork rightFork) {
        super(childDescription.getName(), childDescription.getHungerSpeedMs());
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (true) {
            waitForHungry();

            leftFork.take();
            rightFork.take();
            eat();
            rightFork.putDown();
            leftFork.putDown();
        }
    }

    private void waitForHungry(){
        try {
            Thread.sleep(5 * hungerSpeed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
