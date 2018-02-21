package mitjavawordnetinterface.Main;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class WordCounter {
    private String text; //essay text as a string
    private int severityCount; //proportion of total word count; determined by user
    private String[] splitText;//array of words - to be used by parseText()

    //Constructor takes essay as a String and a severity count; initializes splitText to NULL
    public WordCounter(String text, int count) {
        this.text = text;
        severityCount = count;
        splitText = null;
    }

    //Accessor method for split text
    public String[] getSplitText() {
        return splitText;
    }

    //Parses splitText and splits into array of words based on "_" (spaces)
    public void parseText() {
        String delims = "[ ]+";
        splitText = text.split(delims);
    }

    //Organizes splitText alphabetically
    //NOTE: Uses insertion sort algorithm; problem size is small
    public void sortArray() {
        for (int i = 1; i < splitText.length; i++) {
            String temp = splitText[i];
            int pos = i;
            // Shuffle up all sorted items > splitText[i]
            while (pos > 0 && splitText[pos - 1].compareToIgnoreCase(temp) > 0) {
                splitText[pos] = splitText[pos - 1];
                pos--;
            }
            // Insert the current item
            splitText[pos] = temp;
        }
    }

    //REQUIRES: splitText is sorted in alphabetical order
    //Returns key-value pair of words and their counts in splitText that appear severityCount+ number of times
    public Map getWords() {
        //LinkedHashMap to preserve ordering: key is word, value is count
        Map<String, Integer> wordsToChange = new LinkedHashMap<>();
        int count = 1;
        for (int i = 0; i < splitText.length - 1; i++) {
            if (splitText[i].equals(splitText[i + 1])) {
                count++;
                if (count >= severityCount) {
                    //if wordsToChange does not contain the current word
                    if(!wordsToChange.containsKey(splitText[i])){
                        //add to key-value pair
                        wordsToChange.put(splitText[i], count);
                    }
                    else{
                        //otherwise if count is bigger than count of the one in k-v pair, update
                        if(count > wordsToChange.get(splitText[i]) ){
                            wordsToChange.remove(splitText[i]);
                            wordsToChange.put(splitText[i], count);
                        }
                    }
                }
            }
            else {
                count = 1;
            }
        }
        return wordsToChange;
    }

    //OVERRIDE: Equals; equal if strings values are the same
    //NOTE: May not need to override equals, debug if needed
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordCounter that = (WordCounter) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}