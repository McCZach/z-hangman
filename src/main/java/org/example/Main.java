package org.example;

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
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String word = getWord();



    }
}