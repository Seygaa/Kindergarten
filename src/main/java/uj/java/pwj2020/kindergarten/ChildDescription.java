package uj.java.pwj2020.kindergarten;

public class ChildDescription {
    private final String name;
    private final int hungerSpeedMs;

    public ChildDescription(String childData) {
        String[] readData = childData.split(" ");
        this.name = readData[0];
        this.hungerSpeedMs = Integer.parseInt(readData[1]);
    }

    public String getName() {
        return name;
    }

    public int getHungerSpeedMs() {
        return hungerSpeedMs;
    }
}
