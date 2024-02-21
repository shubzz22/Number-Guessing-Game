import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private int lowerBound = 1;
    private int upperBound = 100;
    private int targetNumber;
    private int maxAttempts = 10;
    private int attempts = 0;

    private JTextField guessField;
    private JTextArea resultArea;

    public NumberGuessingGameGUI() {
        super("Number Guessing Game");

        initializeGame();

        JLabel guessLabel = new JLabel("Enter your guess:");
        guessField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(guessLabel);
        panel.add(guessField);
        panel.add(guessButton);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void initializeGame() {
        Random random = new Random();
        targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        attempts = 0;
        resultArea.setText("Welcome to the Number Guessing Game!\nI have selected a number between 1 and 100. Try to guess it.");
    }

    private void handleGuess() {
        String guessText = guessField.getText();
        if (!guessText.isEmpty()) {
            int userGuess = Integer.parseInt(guessText);
            attempts++;

            if (userGuess == targetNumber) {
                resultArea.append("\nCongratulations! You guessed the correct number in " + attempts + " attempts.");
                initializeGame();
            } else if (userGuess < targetNumber) {
                resultArea.append("\nToo low. Try again.");
            } else {
                resultArea.append("\nToo high. Try again.");
            }

            int remainingAttempts = maxAttempts - attempts;
            if (remainingAttempts > 0) {
                resultArea.append("\nYou have " + remainingAttempts + " attempts remaining.");
            } else {
                resultArea.append("\nSorry, you've run out of attempts. The correct number was: " + targetNumber);
                initializeGame();
            }

            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGameGUI());
    }
}
