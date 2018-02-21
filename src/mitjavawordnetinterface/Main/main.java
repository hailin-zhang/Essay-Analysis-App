package mitjavawordnetinterface.Main;

import javax.swing.*;
import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        JFrame frame = new JFrame("Essay Analyzer - Hai Lin Zhang.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Hello, this is an application to improve the quality of your essay. \n" +
                "It will provide synonyms for words that we believe you have used too often. \n" +
                "Please select a level of toughness, then copy and paste your essay into the following popup dialogue! \n" +
                "Special thanks to MIT for their JWI.");
        String[] options = {"Light", "Medium", "Tough", "Impossible"};
        //CHOICE NUMBER: 0 = light, 1 = medium, 2 = tough, 3 = impossible
        int choiceNumber = JOptionPane.showOptionDialog(null, "Choose a level of toughness: ",
                "Essay Analyzer - Hai Lin Zhang", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,
                options, options[0]);

        String essay = JOptionPane.showInputDialog("Please copy & paste your essay here: ");
        JOptionPane.showMessageDialog(frame, "Your essay: " + essay);

        int severityCount = 0;
        switch(choiceNumber) {
            case 1:
                severityCount = 4;
                break;
            case 2:
                severityCount = 3;
                break;
            case 3:
                severityCount = 2;
                break;
            default:
                severityCount = 5;
                break;
        }

        WordCounter cur = new WordCounter(essay, severityCount);
        cur.parseText();
        JOptionPane.showMessageDialog(frame, "Analyzing your essay ...");
        cur.sortArray();
        SynonymGetter syns = new SynonymGetter(cur.getWords());
        syns.getWordSynonyms();

        for(int i = 0; i < syns.getSynonyms().size(); i++) {
            Integer value = (new ArrayList<Integer>(syns.getWordsToChange().values())).get(i);
            JOptionPane.showMessageDialog(frame,
                    "Here's a word you used too often: " +  syns.getWordsToChange().keySet().toArray()[i] + ".\n" +
                            "It's been used " + value + " times.");
            JOptionPane.showMessageDialog(frame,
                    "Here are some words you could use instead: "+ syns.getSynonyms().get(i));
        }
        JOptionPane.showMessageDialog(frame, "Thank you for using my Essay Analysis App!");
    }
}
