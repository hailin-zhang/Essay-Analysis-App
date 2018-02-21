package mitjavawordnetinterface.Main;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SynonymGetter {
    private Map wordsToChange;
    private List synonyms;

    //Constructor takes a key-value pair (Map) of the form word:count. Initializes synonym list to empty
    public SynonymGetter(Map wordsToChange) {
        this.wordsToChange = wordsToChange;
        synonyms = new ArrayList<String>();
    }

    //Accessor for list of synonyms
    public List getSynonyms() {
        return synonyms;
    }

    //Accessor for map of words to change
    public Map getWordsToChange() {
        return wordsToChange;
    }

    //Gets synonyms for each word in map; adds to synonyms in order of wordsToChange
    public void getWordSynonyms() {
        wordsToChange.forEach((k, v) -> {
            try {
                //adds a set of synonyms to synonyms for each key-value pair
                synonyms.add(getSynonym((String) k));
            } catch (IOException ignored) {
                //Exception ignored (for now... ?)
            }
        });
    }

    //Helper to get the synonyms of a single word
    private Set getSynonym(String k) throws IOException {
        //Initializes library/dictionary
        String wordNetDirectory = "C:\\Users\\Hailin\\IdeaProjects\\untitled\\src\\mitjavawordnetinterface\\Main\\WordNet-3.0";
        String path = wordNetDirectory + File.separator + "dict";
        URL url = new URL("file", null, path);
        IDictionary dict = new Dictionary(url);
        dict.open();

        //Gets synonyms fom dictionary
        Set<String> synonyms = new HashSet<>();
        //Could be a noun, verb, etc.; might let user decide in future versions
        for (POS p : POS.values()) {
            IIndexWord wordIndex = dict.getIndexWord((String) k, p);
            if (wordIndex != null) {
                IWordID wordID = wordIndex.getWordIDs().get(0);
                IWord word = dict.getWord(wordID);
                ISynset synset = word.getSynset();
                for (IWord w : synset.getWords()) {
                    synonyms.add(w.getLemma());
                }
            }
        }
        return synonyms;
    }
}
