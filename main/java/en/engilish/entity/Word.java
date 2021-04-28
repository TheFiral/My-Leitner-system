package en.engilish.entity;


import java.util.Objects;

public class Word {

    private int id;
    private String englishTranslate;
    private String russianTranslate;
    private int count;

    public Word() {

    }

    public Word(int id, String russianTranslate, String englishTranslate, int count) {
        this.id = id;
        this.englishTranslate = englishTranslate;
        this.russianTranslate = russianTranslate;
        this.count = count;
    }

    public String getEnglishTranslate() {
        return englishTranslate;
    }

    public void setEnglishTranslate(String englishTranslate) {
        this.englishTranslate = englishTranslate;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return id == word.id &&
                count == word.count &&
                Objects.equals(englishTranslate, word.englishTranslate) &&
                Objects.equals(russianTranslate, word.russianTranslate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, englishTranslate, russianTranslate, count);
    }
}
