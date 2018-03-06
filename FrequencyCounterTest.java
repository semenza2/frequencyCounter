import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FrequencyCounterTest {
    @Before
    public void setUp() throws Exception {
        URL oracle = new URL("file:///Users/SarahMenza/Documents/Test.txt");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            //replaces anything that is not a character from a - z with a space
            FrequencyCounter.allChars = inputLine.replaceAll("[^A-Za-z]+", " ");
            FrequencyCounter.allChars = FrequencyCounter.allChars.toLowerCase();
            FrequencyCounter.words.addAll(Arrays.asList(FrequencyCounter.allChars.trim().split("\\s+")));
        }
        FrequencyCounter.countOccurrences();
        FrequencyCounter.orderedArrayList();
    }

    @Test
    public void tenMostFrequent() {
        int initialSize = FrequencyCounter.wordFrequencies.size();
        FrequencyCounter.tenMostFrequent();
        int newSize = FrequencyCounter.wordFrequencies.size();
        assertTrue(initialSize - 10 == newSize);

    }

    @Test
    public void countOccurrences() {
        assertTrue(9 == FrequencyCounter.wordFrequencies.get("have"));
    }

    @Test
    public void orderedArrayList() {
        assertTrue(FrequencyCounter.occurrences.get(0) > FrequencyCounter.occurrences.get(1) &&
        FrequencyCounter.occurrences.get(1) >FrequencyCounter.occurrences.get(2));
    }
}