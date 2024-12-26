import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Hangman Game");

        // List of secret words
        String[] words = { "strength", "literacy", "chemistry", "exchange", "aluminium",
                "pause", "pleasure", "misery", "knife", "carbon", "contemporary", "influence",
                "dilemma", "sodium", "double", "rabbit", "publicity", "mild", "maid", "exception" };

        // Choose a random secret word
        String secretWord = words[random.nextInt(words.length)];

        // Create a list to hold the current state of guessed letters
        ArrayList<Character> wordState = new ArrayList<>();

        // Counter for wrong guesses
        int wrongGuesses = 0;

        // Initialize wordState with underscores for each letter in the secret word
        for (int i = 0; i < secretWord.length(); i++) {
            wordState.add('_');
        }

        //Game Loop
        do {

            // Display the current state of the guessed word
            System.out.print("Guess the word: ");
            for (Character character : wordState) {
                System.out.print(character);
            }
            System.out.println();

            // Prompt the user to guess a letter
            System.out.print("Guess a letter: ");
            char guessLetter = scanner.nextLine().toLowerCase().charAt(0);

            // Check if the guessed letter is in the secret word
            if (secretWord.indexOf(guessLetter) >= 0) {
                System.out.println("Correct answer!");
                // Update wordState with the correctly guessed letter
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == guessLetter) {
                        wordState.set(i, guessLetter);
                    }
                }
            } else {
                System.out.println("Wrong answer!");
                // Increment the wrong guesses counter
                wrongGuesses++;
            }
            // Display the current hangman state
            System.out.println(printHangman(wrongGuesses));
        } while (wrongGuesses < 6 && wordState.contains('_'));

        // Check if the user has won or lost the game
        if (!wordState.contains('_')) {
            System.out.println("Congratulations! You guessed the word: " + secretWord);
        } else {
            System.out.println("Sorry, you lost! The secret word was: " + secretWord);
        }
        scanner.close();
    }

    // Method to return the hangman drawing based on the number of wrong guesses
    public static String printHangman(int wrongGuesses) {
        return switch (wrongGuesses) {
            case 0 -> """



                    """;
            case 1 -> """
                     O


                    """;
            case 2 -> """
                     O
                     |


                    """;
            case 3 -> """
                     O
                    /|

                    """;
            case 4 -> """
                     O
                    /|\\

                    """;
            case 5 -> """
                     O
                    /|\\
                    /
                    """;
            case 6 -> """
                     O
                    /|\\
                    / \\
                    """;
            default -> " ";
        };
    }
}
