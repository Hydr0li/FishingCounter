package edu.bu.met.cs665;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Fisher implements Observer, Runnable {
    private String name;
    private edu.bu.met.cs665.Observable observable;

    public Fisher(String name, edu.bu.met.cs665.Observable observable) {
        this.name = name;
        this.observable = observable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(random.nextInt(5000));
                observable.notifyObservers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null && arg instanceof Boolean && (Boolean) arg) {
            System.out.println(name + " failed to land the fish due to an accident.");
        } else {
            System.out.println(name + " is fishing...");
        }
    }
}
