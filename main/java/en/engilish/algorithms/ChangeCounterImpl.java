package en.engilish.algorithms;

import en.engilish.dao.WordsRepo;
import en.engilish.entity.Word;

public class ChangeCounterImpl implements ChangeCounter {

    @Override
    public int changeCount(Word word, int i, int min, int max, WordsRepo db) {

        int sum = word.getCount() + i;

        if (sum == max) {

            db.insertWord(word.getEnglishTranslate(), word.getRussianTranslate(), 0);
            db.deletedWord(word);
            return 1;

        } else if ((sum >= min) && (sum < max)) {

            db.updateWord(word, sum);
            return -1;

        } else {
            return 0;
        }
    }
}
