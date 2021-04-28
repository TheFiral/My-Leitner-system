package en.engilish.entity;

import java.util.LinkedList;
import java.util.List;

public class CheckingWords {

    private static CheckingWords INSTANCE = null;
    private List<Word> listCheckingWords;

    private CheckingWords(){
        this.listCheckingWords = new LinkedList<>();
    }

    public static CheckingWords getInstance(){
        if(INSTANCE == null){
            INSTANCE = new CheckingWords();
        }
        return INSTANCE;
    }

    public List<Word> getListCheckingWords() {
        return listCheckingWords;
    }

    public void setListCheckingWords(List<Word> listCheckingWords) {
        this.listCheckingWords = listCheckingWords;
    }
}
