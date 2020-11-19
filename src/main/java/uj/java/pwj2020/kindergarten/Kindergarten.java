package uj.java.pwj2020.kindergarten;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Kindergarten extends Thread {

    public static void main(String[] args) throws IOException {
        File myFile = validateInputAndReturnFile(args);
        if(Objects.isNull(myFile)) {
            return;
        }
        init();
        new Kindergarten().startDinner(myFile);
    }

    private void startDinner(File myFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(myFile)) {
            int childAmount = Integer.parseInt(scanner.nextLine());

            Fork[] forks = createForks(childAmount);
            for (int i = 0; i < childAmount; i++) {
                String childData = scanner.nextLine();
                startChild(childData, forks, i);
            }
        }
    }

    private void startChild(String childData, Fork[] forks, int childNumber){
        Preschooler child = new Preschooler(
                new ChildDescription(childData),
                getLeftFork(forks, childNumber),
                getRightFork(forks, childNumber, forks.length)
        );
        Thread t1 = new Thread(child);
        t1.start();
    }

    private Fork getLeftFork(Fork[] forks, int i){
        return forks[i];
    }

    private Fork getRightFork(Fork[] forks, int i, int childAmount){
        return forks[(i + 1) % childAmount];
    }

    private Fork[] createForks(int childAmount){
        Fork[] forks = new Fork[childAmount];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork();
        }
        return forks;
    }

    private static File validateInputAndReturnFile(String[] args){
        if(args.length==0){
            System.err.println("Give me a filename.");
            return null;
        }
        String fileName = args[0];
        File myFile = new File(fileName);
        if(!myFile.exists()){
            System.err.printf("File %s does not exist.%n", fileName);
            return null;
        }
        return myFile;
    }

    private static void init() throws IOException {
        Files.deleteIfExists(Path.of("out.txt"));
        System.setErr(new PrintStream(new FileOutputStream("out.txt")));
        new Thread(Kindergarten::runKindergarden).start();
    }

    private static void runKindergarden() {
        try {
            Thread.sleep(10100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            List<String> errLines = Files.readAllLines(Path.of("out.txt"));
            System.out.println("Children cries count: " + errLines.size());
            errLines.forEach(System.out::println);
            System.exit(errLines.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

