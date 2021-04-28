package en.engilish.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import en.engilish.R;
import en.engilish.algorithms.ChangeCounter;
import en.engilish.algorithms.ChangeCounterImpl;
import en.engilish.dao.WordsCAWImpl;
import en.engilish.dao.WordsRepo;
import en.engilish.entity.CheckingWords;
import en.engilish.entity.Word;

public class CheckAllWord extends AppCompatActivity {

    private WordsRepo db;
    private ChangeCounter counter;

    private CheckingWords list;
    private Word currentWord;
    private int i;
    private boolean checkWord;

    private String wordForView;
    private String wordForTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_all_word);

        db = new WordsCAWImpl();
        counter = new ChangeCounterImpl();

        list = CheckingWords.getInstance();
        list.setListCheckingWords(db.getAllWords());

        i = 1;
        currentWord = list.getListCheckingWords().get(0);
        appointTranslate(currentWord);
    }

    public void getNextOldWord(View view) {

        EditText editText = findViewById(R.id.editTextCheckAllWord);
        String string = editText.getText().toString();
        TextView textView = findViewById(R.id.textViewCheckAllWord);

        if (!checkWord) {

            if (string.equals(wordForTranslate)) {

                textView.setText(wordForView + "\n" + wordForTranslate);
                editText.getText().clear();
                editText.setHint("it's true!" + "\n" + " Please press next that you can get next word.");
                checkWord = true;
                counter.changeCount(currentWord, -1, 0, 4, db);

            } else if (string.isEmpty()) {

                textView.setText(wordForView + "\n" + wordForTranslate);
                editText.getText().clear();
                editText.setHint("Field is empty!" + "\n" + " Please press next that you can get next word.");
                checkWord = true;
                counter.changeCount(currentWord, 1, 0, 4, db);

            } else {
                textView.setText(wordForView + "\n" + wordForTranslate);
                editText.getText().clear();
                editText.setHint("It's wrong translate!" + "\n" + " Please press next that you can get next word.");
                checkWord = true;
                counter.changeCount(currentWord, 1, 0, 4, db);
            }

        } else {

            if (i < list.getListCheckingWords().size()) {

                currentWord = list.getListCheckingWords().get(i);
                appointTranslate(currentWord);
                i++;
                checkWord = false;
                editText.setHint("write here translate this word");

            } else {

                list.getListCheckingWords().clear();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();

            }

        }
    }

    public void getBackOnMainPageFromCAW(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void appointTranslate(Word word) {

        int count = word.getCount();

        if ((count == 0) || (count == 2)) {

            wordForView = word.getRussianTranslate();
            TextView textView = findViewById(R.id.textViewCheckAllWord);
            textView.setText(wordForView);
            wordForTranslate = word.getEnglishTranslate();

        } else if ((count == 3) || (count == 1)) {

            wordForView = word.getEnglishTranslate();
            TextView textView = findViewById(R.id.textViewCheckAllWord);
            textView.setText(wordForView);
            wordForTranslate = word.getRussianTranslate();

        } else {

            db.insertWord(word.getEnglishTranslate(), word.getRussianTranslate(), 0);
            db.deletedWord(word);

        }
    }
}