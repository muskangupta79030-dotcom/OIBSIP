import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int round = 1;
        ArrayList<String> roundResults = new ArrayList<>();
        String playAgain = "yes";

        System.out.println("========================================");
        System.out.println("        NUMBER GUESSING GAME");
        System.out.println("========================================");

        while (playAgain.equalsIgnoreCase("yes")) {

            System.out.println("\n========================================");
            System.out.println("              ROUND " + round);
            System.out.println("========================================");

            // Difficulty Selection
            System.out.println("\nSelect Difficulty:");
            System.out.println("1. Easy   (1-50, 10 attempts)");
            System.out.println("2. Medium (1-100, 7 attempts)");
            System.out.println("3. Hard   (1-200, 5 attempts)");

            System.out.print("Enter your choice: ");

            int choice;

            // Validate difficulty input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter 1, 2, or 3.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }

            choice = scanner.nextInt();

            int maxNumber;
            int maxAttempts;

            if (choice == 1) {

                maxNumber = 50;
                maxAttempts = 10;

                System.out.println("Difficulty selected: Easy");

            }
            else if (choice == 2) {

                maxNumber = 100;
                maxAttempts = 7;

                System.out.println("Difficulty selected: Medium");

            }
            else if (choice == 3) {

                maxNumber = 200;
                maxAttempts = 5;

                System.out.println("Difficulty selected: Hard");

            }
            else {

                System.out.println(
                    "Invalid choice! Medium difficulty selected."
                );

                maxNumber = 100;
                maxAttempts = 7;
            }

            // Generate random number
            int number = random.nextInt(maxNumber) + 1;

            // Game instructions
            System.out.println("\nGuess the secret number!");
            System.out.println("You have " + maxAttempts + " attempts.");
            System.out.println("Number range: 1 to " + maxNumber);

            int attempts = 0;
            boolean guessedCorrectly = false;

            // Guessing loop
            while (attempts < maxAttempts) {

                System.out.print("\nEnter your guess: ");

                // Validate numeric input
                if (!scanner.hasNextInt()) {

                    System.out.println(
                        "Invalid input! Please enter a number."
                    );

                    scanner.next();
                    continue;
                }

                int guess = scanner.nextInt();

                // Validate number range
                if (guess < 1 || guess > maxNumber) {

                    System.out.println(
                        "Please enter a number between 1 and "
                        + maxNumber + "."
                    );

                    continue;
                }

                // Increase attempt counter
                attempts++;

                System.out.println(
                    "Attempt: " + attempts + "/" + maxAttempts
                );

                System.out.println(
                    "Attempts remaining: "
                    + (maxAttempts - attempts)
                );

                // Compare guess with random number
                if (guess > number) {

                    System.out.println("Too High!");

                }
                else if (guess < number) {

                    System.out.println("Too Low!");

                }
                else {

                    System.out.println(
                        "\nCorrect! You guessed the number!"
                    );

                    guessedCorrectly = true;
                    break;
                }
            }

            // Round result
            if (guessedCorrectly) {

                String result =
                    "Round " + round
                    + " — guessed in "
                    + attempts
                    + " attempts.";

                System.out.println("\n" + result);

                roundResults.add(result);

            }
            else {

                System.out.println("\nYou Lost!");

                System.out.println(
                    "The correct number was: " + number
                );

                String result =
                    "Round " + round + " — You Lost.";

                System.out.println(result);

                roundResults.add(result);
            }

            // Play Again
            System.out.print(
                "\nDo you want to play again? (yes/no): "
            );

            playAgain = scanner.next();

            round++;
        }

        // Final Game Summary
        System.out.println(
            "\n========================================"
        );

        System.out.println(
            "             GAME SUMMARY"
        );

        System.out.println(
            "========================================"
        );

        for (String result : roundResults) {

            System.out.println(result);
        }

        System.out.println(
            "----------------------------------------"
        );

        System.out.println(
            "Total Rounds Played: "
            + roundResults.size()
        );

        System.out.println(
            "========================================"
        );

        System.out.println(
            "\nThanks for playing Number Guessing Game!"
        );

        scanner.close();
    }
}


