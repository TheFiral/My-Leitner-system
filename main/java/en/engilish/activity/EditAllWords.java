package en.engilish.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import en.engilish.R;
import en.engilish.dao.WordsCAWImpl;
import en.engilish.dao.WordsLNWImpl;
import en.engilish.dao.WordsRepo;
import en.engilish.entity.AnotherWord;
import en.engilish.entity.Word;

public class EditAllWords extends AppCompatActivity {

    private WordsRepo caw;
    private WordsRepo lnw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_all_words);

        lnw = new WordsLNWImpl();
        caw = new WordsCAWImpl();

        ArrayList<Word> listLNW = (ArrayList<Word>) lnw.getAllWords();
        ArrayList<Word> listCAW = (ArrayList<Word>) caw.getAllWords();
        ArrayList<AnotherWord> list = new ArrayList<>();

        for (Word word : listLNW) {
            list.add(new AnotherWord(word, new WordsLNWImpl()));
        }

        for (Word word : listCAW) {
            list.add(new AnotherWord(word,new WordsCAWImpl()));
        }

        ListView wordList = findViewById(R.id.productList);
        WordAdapter adapter = new WordAdapter(this, R.layout.activity_list_word, list);
        wordList.setAdapter(adapter);
    }
}