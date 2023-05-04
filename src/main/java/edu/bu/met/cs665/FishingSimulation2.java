package edu.bu.met.cs665;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FishingSimulation2 {
    /**
    public static void main(String[] args) {
        FishingProcess fishingProcess = new FishingProcessImpl();

        // Start multiple fishers
        for (int i = 0; i < 5; i++) {
            Fisher2 fisher = new Fisher2(fishingProcess);
            fisher.start();
        }
    }
    */
    private JFrame frame;
    public int count;

    public FishingSimulation2() {
        // Create the frame
        frame = new JFrame("Fishing Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);

        // Create the button
        JButton startButton = new JButton("Start Fishing");

        // Create a panel and add the button to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        System.out.println("Number of components in buttonPanel: " + buttonPanel.getComponentCount()); // Print the number of components in buttonPanel
        // Add the panel to the frame
        frame.add(buttonPanel);

        // Add an action listener to the button to start the fishing process
        startButton.addActionListener(e -> {
            FishingProcess fishingProcess = new FishingProcessImpl();
           count = 0;
            // Start multiple fishers
            for (int i = 0; i < 5; i++) {
                Fisher2 fisher = new Fisher2(fishingProcess);
                fisher.start();
            }
        });
    }

    public static void main(String[] args) {
        //new FishingSimulation2();
        FishingProcess fishingProcess = new FishingProcessImpl();

        // Start multiple fishers
        for (int i = 0; i < 5; i++) {
            Fisher2 fisher = new Fisher2(fishingProcess);
            fisher.start();
        }
    }



}
