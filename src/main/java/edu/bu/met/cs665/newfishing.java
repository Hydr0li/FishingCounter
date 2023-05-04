package edu.bu.met.cs665;

import java.util.Random;
import java.util.concurrent.TimeUnit;

interface FishingProcess {
    void fish() throws InterruptedException;
    void clean() throws InterruptedException;
}

class FishingProcessImpl implements FishingProcess {
    private boolean fishLanded = false;
    private boolean accidentOccurred = false;
    private boolean fishingInProgress = false;
    private Random random = new Random();
    private int count = 0;

    public synchronized void fish() throws InterruptedException {
        while (fishingInProgress || fishLanded || accidentOccurred) {
            wait(); // Balking: wait until the previous process is completed
        }

        int result = random.nextInt(10);
        if (result <= 4) {
            // Accident occurred
            System.out.println("Hooked the fish!");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Reeling...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Reeling...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("An accident occurred and we failed to land the fish!");
            accidentOccurred = true;
        } else {
            // Successfully landed the fish
            System.out.println("Hooked the fish!");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Reeling...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Reeling...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("We caught a fish!");
            fishLanded = true;
        }

        fishingInProgress = true;
        notifyAll(); // Notify waiting threads
    }

    public synchronized void clean() throws InterruptedException {
        while (fishingInProgress || !fishLanded) {
            wait(); // Balking: wait until a fish is landed and there's no fishing process in progress
        }

        System.out.println("Cleaning the fish...");
        Thread.sleep(2000); // Simulate cleaning process

        fishLanded = false;
        accidentOccurred = false;
        fishingInProgress = false;

        notifyAll(); // Notify waiting threads
    }
}

class Fisher2 extends Thread {
    private FishingProcess fishingProcess;

    public Fisher2(FishingProcess fishingProcess) {
        this.fishingProcess = fishingProcess;
    }

    @Override
    public void run() {
        try {
            fishingProcess.fish();
            fishingProcess.clean();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



