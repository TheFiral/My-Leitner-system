package en.engilish;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import en.engilish.algorithms.ChangeCounter;
import en.engilish.algorithms.ChangeCounterImpl;
import en.engilish.dao.WordsRepo;
import en.engilish.entity.Word;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ChangeCounterImplTest {

    private ChangeCounter counter;
    private Word word;

    private int first;
    private int min;
    private int max;
    private int currentCountWord;
    private int expected;

    public ChangeCounterImplTest(int first, int min, int max, int currentCountWord, int expected) {
        this.first = first;
        this.min = min;
        this.max = max;
        this.currentCountWord = currentCountWord;
        this.expected = expected;
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before ChangeCounter.class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After ChangeCounter.class");
    }

    @Before
    public void initTest() {
        counter = new ChangeCounterImpl();
        word = new Word();
    }

    @After
    public void afterTest() {
        counter = null;
        word = null;
    }


    @Parameterized.Parameters
    public static Collection listObject() {
        return Arrays.asList(new Integer[][]{
                {1, 0, 4, 0, -1},
                {1, 0, 4, 1, -1},
                {1, 0, 4, 2, -1},
                {1, 0, 4, 3, 1},
                {1, 0, 4, 4, 0},
                {-1, 0, 4, 0, 0},
                {-1, 0, 4, 1, -1},
                {-1, 0, 4, 2, -1},
                {-1, 0, 4, 3, -1},
                {-1, 0, 4, 4, -1}
        });
    }

    @Test
    public void testChangeCount() {
        word.setCount(currentCountWord);
        assertEquals(expected, counter.changeCount(word, first, min, max, new WordsRepo() {
            @Override
            public List getAllWords() {
                return null;
            }

            @Override
            public void insertWord(String eng, String ru, int count) {

            }

            @Override
            public void deletedWord(Word word) {

            }

            @Override
            public void updateWord(Word word, int sum) {

            }
        }));
    }
}
