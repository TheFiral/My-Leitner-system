package en.engilish.entity;


public class Word {

    private int id;
    private String englishTranslate;
    private String russianTranslate;
    private int countLearnNewWord;
    private int countCheckAllWord;

    public Word() {

    }

    public Word(int id, String russianTranslate, String englishTranslate,int countLearnNewWord) {
        this.id = id;
        this.englishTranslate = englishTranslate;
        this.russianTranslate = russianTranslate;
        this.countLearnNewWord = countLearnNewWord;
        this.countCheckAllWord = 0;
    }

    public String getEnglishTranslate() {
        return englishTranslate;
    }

    public void setEnglishTranslate(String englishTranslate) {
        this.englishTranslate = englishTranslate;
    }

    public int getCountLearnNewWord() {
        return countLearnNewWord;
    }

    public void setCountLearnNewWord(int countLearnNewWord) {
        this.countLearnNewWord = countLearnNewWord;
    }

    public int getCountCheckAllWord() {
        return countCheckAllWord;
    }

    public void setCountCheckAllWord(int countCheckAllWord) {
        this.countCheckAllWord = countCheckAllWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRussianTranslate() {
        return russianTranslate;
    }

    public void setRussianTranslate(String russianTranslate) {
        this.russianTranslate = russianTranslate;
    }
}
