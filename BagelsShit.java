/*
 * I'm submitting my answer to this question, regardless of its correctness,
 * to GitHub, because I got marks like pieces of shit. I don't think that
 * people should use my shit to do the homework.
 * Fuck the binary marking criteria. They don't know "Error Carried Forward"
 * and give me a fraction of the marks, because the marks are either 0 or 1.
 * I should stop my complaint. I'm doing this to accept plagiarism.
 * take and optimize this algorithm to your homework, if there is one!
 * I encourage your breach of academic honesty.
 */
import java.util.Random;
import java.util.Scanner;

public class BagelsShit {

    public static void main(String[] args) {
        Random r = new Random();
        Scanner console = new Scanner(System.in);
        introduction();
        int numberOfGames = 0;
        int totalGuessMain = 0;
        int guessInOneGame;// the number of guesses one player did in one game
        // assume that nobody guesses more than 9999 times
        int bestGame = 9999;
        // determines if the game should continue
        boolean gameGoingOn = true;
        while (gameGoingOn) {
            numberOfGames++;
            guessInOneGame = playOneGame(r, console);
            totalGuessMain += guessInOneGame;
            bestGame = Math.min(bestGame, guessInOneGame);
            System.out.print("Do you want to play again? ");
            String answerPlayAgain = console.next();
            // check for letter y to start with and is not case sensitive
            if (!answerPlayAgain.toLowerCase().startsWith("y")) {
                gameGoingOn = false;
            }
        }
        resultReport(numberOfGames, totalGuessMain, bestGame);

    }

    // prints the introduction of the game.
    public static void introduction() {
        System.out.println("Welcome to CSE 143x Bagels!");
        System.out.println("I'll think up a number for you to guess.");
        System.out.println("Each digit will be between 1 and 9.");
        System.out.println("Try to guess my number, and I'll say \"fermi\"");
        System.out.println("for each digit you get right and in the right");
        System.out.println("place, and \"pica\" for each digit you get right");
        System.out.println("that is in the wrong place.");
        System.out.println();
    }

    // Plays one game, accepts a random and a scanner object
    public static int playOneGame(Random r, Scanner console) {
        int totalGuess = 0;// initialize the total number of guess
        System.out.print("How many digits this time? ");
        int digit = console.nextInt();
        String answer = "";// initialize the answer
        boolean correct = false;// whether the user gets the correct answer
        answer = randomAnswer(answer, digit, r);
        String guess;

        while (!correct) {
            System.out.print("Your guess? ");
            guess = console.next();
            if (guess.length() != answer.length()) {
                System.out.println("Invalid input, please try again");
                return totalGuess;
            }
            totalGuess++;// increases the number of guess
            if (guess.equals(answer)) {
                System.out.println("You got it right in " + totalGuess + " guesses");
                correct = true;
            }
            // if true, the whole loop stops, so else is used
            // instead of just another if
            else {
                // fermi, pica and/or bagels
                fermiPicaBagels(answer, guess);
            }
        }

        return totalGuess;
    }

    // generate random answer key for a bagels game, takes a
    // placeholder string, a number of digits, and a random object
    public static String randomAnswer(String answer, int digit, Random r) {

        for (int i = 0; i < digit; i++) {
            answer += r.nextInt(9) + 1;
        }
        return answer;
    }

    // prints out fermi, pica or bagels as needed
    // needs the comparison of "answer" and "guess" strings.
    public static void fermiPicaBagels(String answer, String guess) {
        // fermi
        String hint = "";// the complete hint which will be returned by the end
        for (int x = 0; x < answer.length(); x++) {
            if (guess.charAt(x) == answer.charAt(x)) {
                hint += "fermi ";
                // replaces fermi-verified digits with x in answer
                answer = replaceDigit(answer, x, "");
                // replace checked digits with "y"
                guess = replaceDigit(guess, x, "");
            }
        }

        // pica is only represented once
        boolean pica = false;
        for (int x = 0; x < guess.length(); x++) {
            for (int y = 0; y < answer.length(); y++) {
                if (guess.charAt(x) == answer.charAt(y)) {
                    // prevents pica from being returned multiple times
                    pica = true;
                    // replace pica-verified answers with "z"
                    answer = replaceDigit(answer, y, "");
                    guess = replaceDigit(guess, x, "");
                }
            }
            if (pica) {
                hint += "pica ";
                // get the variable prepared for the next guess
                // also prevents multiple picas for the same digit
                pica = false;
            }
        }
        // returns "bagels" or the hint which consists of fermi and pica
        if (hint.equals("")) {
            System.out.println("bagels");
        } else {
            // remove the last space from the value of hint
            hint = hint.substring(0, hint.length() - 1);
            System.out.println(hint);
        }
    }

    // replaces target character in a string with x
    public static String replaceDigit(String answer, int index, String alphabet) {
        if (index == answer.length() - 1) {
            // when index is the at the last of the string,
            // just replace it with "x"
            return answer.substring(0, index) + alphabet;
        } else {
            // otherwise, insert "x" in between the left and the right
            // of target char, and get rid of the char
            return answer.substring(0, index) + alphabet + answer.substring(index + 1);
        }
    }

    // prints out game report when a user stops playing
    public static void resultReport(int numberOfGames, int totalGuess, int bestGame) {
        System.out.println();
        System.out.println("Overall results:");
        System.out.println("    total games   = " + numberOfGames);
        System.out.println("    total guesses = " + totalGuess);
        System.out.println("    guesses/game  = " + ((double) totalGuess / numberOfGames));
        System.out.println("    best game     = " + bestGame);
    }
}
