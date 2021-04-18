package en.engilish.entity;

import java.util.LinkedList;
import java.util.List;

public class CheckingWords {

    private static CheckingWords INSTANCE = null;
    private List<Word> listLearningWords;

    private CheckingWords(){
        this.listLearningWords = new LinkedList<>();
    }

    public static CheckingWords getInstance(){
        if(INSTANCE == null){
            INSTANCE = new CheckingWords();
        }
        return INSTANCE;
    }

    public List<Word> getListLearningWords() {
        return listLearningWords;
    }

    public void setListLearningWords(List<Word> listLearningWords) {
        this.listLearningWords = listLearningWords;
    }
}
