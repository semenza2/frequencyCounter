import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;

public class URLloader {


    public static void main(String[] args) throws Exception {

        ArrayList<String> words = new ArrayList<>();
        String allChars;
        //https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
        //the below content to read in a URL was taken from Oracle

        URL oracle = new URL("file:///Users/SarahMenza/Documents/Test.txt");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            //replaces anything that is not a character from a - z with a space
            allChars = inputLine.replaceAll("[^A-Za-z]+", " ");
            //new line??
            allChars = allChars.toLowerCase();
            words.addAll(Arrays.asList(allChars.trim().split("\\s+")));
        }

        HashMap<String,Integer> wordFrequencies =new HashMap<String,Integer>();

        //counting occurrences of each word
        for (String word: words) {
            if (wordFrequencies.containsKey(word)) {
                wordFrequencies.put(word, wordFrequencies.get(word) + 1);
            } else {
                wordFrequencies.put(word, 1);
            }
        }
        ArrayList<Integer> occurrences = new ArrayList<>();

        for (String key: wordFrequencies.keySet()) {
           occurrences.add(wordFrequencies.get(key));

        }
        //https://beginnersbook.com/2013/12/sort-arraylist-in-descending-order-in-java/
        //line 49 was googled
        Collections.sort(occurrences, Collections.reverseOrder());

        for (int i = 0; i < 10; i++) {
            for (String key: wordFrequencies.keySet()) {
                if (wordFrequencies.get(key) == occurrences.get(i)) {
                    System.out.println(key + ": " + occurrences.get(i));
                    wordFrequencies.remove(key);
                    break;
                }
            }
        }

        in.close();
    }

}
