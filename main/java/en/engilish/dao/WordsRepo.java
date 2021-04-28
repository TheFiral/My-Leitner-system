package en.engilish.dao;

import java.util.List;

import en.engilish.entity.Word;

public interface WordsRepo {

    List getAllWords();

    void insertWord(String eng, String ru, int count);

    void deletedWord(Word word);

    void updateWord(Word word, int sum);
}
