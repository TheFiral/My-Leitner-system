package en.engilish.algorithms;

import en.engilish.dao.WordsRepo;
import en.engilish.entity.Word;

public interface ChangeCounter {
    int changeCount(Word word, int i, int min, int max, WordsRepo db);
}
