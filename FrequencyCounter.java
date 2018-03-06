import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;

public class FrequencyCounter {

    public static ArrayList<String> words = new ArrayList<>();
    public static String allChars;
    public static HashMap<String,Integer> wordFrequencies =new HashMap<String,Integer>();
    public static ArrayList<Integer> occurrences = new ArrayList<>();

    /**
     * counts occurrences of each word
     */
    public static void countOccurrences() {
        for (String word: words) {
            if (wordFrequencies.containsKey(word)) {
                wordFrequencies.put(word, wordFrequencies.get(word) + 1);
            } else {
                wordFrequencies.put(word, 1);
            }
        }
    }

    /**
     * adds the word counts to an array list and
     * orders it from highest to lowest
     */
    public static void orderedArrayList() {
        for (String key: wordFrequencies.keySet()) {
            occurrences.add(wordFrequencies.get(key));

        }
        /**
         * https://beginnersbook.com/2013/12/sort-arraylist-in-descending-order-in-java/
         * the line below was googled
         * arranges elements highest to lowest
         */

        Collections.sort(occurrences, Collections.reverseOrder());
    }

    /**
     * prints out the ten words with with highest frequency
     */
    public static void tenMostFrequent() {
        if(occurrences.size() >= 10) {
            for (int i = 0; i < 10; i++) {
                for (String key : wordFrequencies.keySet()) {
                    if (wordFrequencies.get(key) == occurrences.get(i)) {
                        System.out.println(key + ": " + occurrences.get(i));
                        wordFrequencies.remove(key);
                        break;
                    }
                }
            }
        } else {
            System.out.println("File does not contain at least 10 different words.");
        }
    }


    public static void main(String[] args) throws Exception {


        //https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
        //the below content to read in a URL was taken from Oracle

        URL oracle = new URL("file:///Users/SarahMenza/Documents/Test.txt");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            //replaces anything that is not a character from a - z with a space
            allChars = inputLine.replaceAll("[^A-Za-z]+", " ");
            allChars = allChars.toLowerCase();
            words.addAll(Arrays.asList(allChars.trim().split("\\s+")));
        }

        countOccurrences();
        orderedArrayList();
        tenMostFrequent();

        in.close();
    }

}
