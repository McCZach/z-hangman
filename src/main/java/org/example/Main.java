package org.example;

import java.util.Arrays;
import java.util.Scanner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main {
    public static String getWord() {
        OkHttpClient client = new OkHttpClient();

        String url = "https://random-word-api.herokuapp.com/word";
        String word = "";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            word = response.body().string();
            word = word.substring(2, word.length() - 2);
        }
        catch (Exception ex) {
            System.out.println("Error in making HTTP request!");
        }

        return word;
    }

    public static char getInput(Scanner scan)
    {
        char input = ' ';

        do {
            try
            {
                System.out.print("Guess: ");
                input = scan.next().charAt(0);
                System.out.println();
            }
            catch (Exception ex)
            {
                System.out.println("Error reading in guess!");
                System.out.println(ex.getMessage());
            }
        } while (!Character.isLetter(input));

        return Character.toLowerCase(input);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String word = getWord();

        char[] guess = new char[word.length()];
        Arrays.fill(guess, '_');
        char letter = ' ';

        int lives = word.length() / 2;
        boolean gameOver, guessSuccess, lost;
        lost = gameOver = guessSuccess = false;


        System.out.println("\n\n***GUESS THAT WORD***\n");
        for (int i = 0; i < word.length(); i++)
        {
            System.out.print(guess[i] + " ");
        }
        System.out.print("\t-> Lives = " + lives + "\n");

        while ((!lost) && (!gameOver))
        {
            letter = getInput(scan);

            for (int i = 0; i < word.length(); i++)
            {
                if (word.charAt(i) == letter)
                {
                    guess[i] = letter;
                    guessSuccess = true;
                }
            }

            if (!guessSuccess)
                lives--;
            guessSuccess = false;

            if (lives == 0)
                lost = true;

            for (int i = 0; i < word.length(); i++)
            {
                System.out.print(guess[i] + " ");
            }
            System.out.print("\t-> Lives = " + lives + "\n");

            if (Arrays.toString(guess).equals(word))
            {
                gameOver = true;
            }
        }

        if (lost)
        {
            System.out.println("\nYou lost - You ran out of guesses! The word was: " + word);
            for (int i = 0; i < word.length(); i++)
            {
                System.out.print(guess[i] + " ");
            }
        }
        else
        {
            System.out.println("You won - With: " + lives + "lives left! The word was: " + word);
        }


    }
}