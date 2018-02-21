package Test;

import mitjavawordnetinterface.Main.WordCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class WordCounterTest {
    private WordCounter text;

    @BeforeEach
    public void runBefore() {
        String testString = "I like cheese I really really really really like cheese";
        text = new WordCounter(testString, 0);
    }

    @Test
    public void testParseText(){
        text.parseText();
        assertEquals(10, text.getSplitText().length);
    }

    @Test
    public void testSortArray(){
        text.parseText();
        text.sortArray();
        String[] currentTextArray = text.getSplitText();
        assertEquals("cheese",currentTextArray[0]);
        assertEquals("cheese",currentTextArray[1]);
        assertEquals("I",currentTextArray[2]);
        assertEquals("like",currentTextArray[5]);
        assertEquals("really", currentTextArray[6]);
        assertEquals("really", currentTextArray[9]);
    }

    @Test
    public void testGetWords(){
        text.parseText();
        text.sortArray();
        Map listOfWords = text.getWords();
        assertEquals(4, listOfWords.size());
        assertTrue(listOfWords.containsKey("cheese"));
        assertEquals(2, listOfWords.get("cheese"));
        assertTrue(listOfWords.containsKey("I"));
        assertEquals(2, listOfWords.get("I"));
        assertTrue(listOfWords.containsKey("like"));
        assertEquals(2, listOfWords.get("like"));
        assertTrue(listOfWords.containsKey("really"));
        assertEquals(4, listOfWords.get("really"));
    }
}
