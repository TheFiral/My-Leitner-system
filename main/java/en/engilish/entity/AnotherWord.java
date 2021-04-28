package en.engilish.entity;

import en.engilish.dao.WordsRepo;

public class AnotherWord extends Word {

    private WordsRepo repo;

    public AnotherWord(Word word, WordsRepo repo) {
        super(word.getId(), word.getRussianTranslate(), word.getEnglishTranslate(), word.getCount());
        this.repo = repo;
    }

    public WordsRepo getRepo() {
        return repo;
    }

    public void setRepo(WordsRepo repo) {
        this.repo = repo;
    }
}
