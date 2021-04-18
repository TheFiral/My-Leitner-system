package en.engilish.entity;

import java.util.LinkedList;
import java.util.List;


public class LearningWords {

    private static LearningWords INSTANCE = null;
    private List<Word> listLearningWords;

    private LearningWords(){
        this.listLearningWords = new LinkedList<>();
    }

    public static LearningWords getInstance(){
        if(INSTANCE == null){
            INSTANCE = new LearningWords();
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
