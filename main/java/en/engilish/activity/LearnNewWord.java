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
import en.engilish.dao.WordsLNWImpl;
import en.engilish.dao.WordsRepo;
import en.engilish.entity.LearningWords;
import en.engilish.entity.Word;

public class LearnNewWord extends AppCompatActivity {

    private WordsRepo db;
    private ChangeCounter counter;

    private LearningWords list;
    private Word currentWord;
    private int i;
    private boolean checkWord;

    private String wordForView;
    private String wordForTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_new_word);

        db = new WordsLNWImpl();
        counter = new ChangeCounterImpl();

        list = LearningWords.getInstance();
        list.setListLearningWords(db.getAllWords());

        i = 1;
        currentWord = list.getListLearningWords().get(0);
        appointTranslate(currentWord);
    }

    public void getNextWord(View view) {

        EditText editText = findViewById(R.id.editTextLearnNewWord);
        String string = editText.getText().toString();
        TextView textView = findViewById(R.id.textViewLearnNewWord);

        if (!checkWord) {

            if (string.equals(wordForTranslate)) {

                textView.setText(wordForView + "\n" + wordForTranslate);
                editText.getText().clear();
                editText.setHint("it's true!" + "\n" + " Please press next that you can get next word.");
                checkWord = true;
                counter.changeCount(currentWord, 1, 0, 4, db);

            } else if (string.isEmpty()) {

                textView.setText(wordForView + "\n" + wordForTranslate);
                editText.getText().clear();
                editText.setHint("Field is empty!" + "\n" + " Please press next that you can get next word.");
                checkWord = true;
                counter.changeCount(currentWord, -1, 0, 4, db);
            } else {

                textView.setText(wordForView + "\n" + wordForTranslate);
                editText.getText().clear();
                editText.setHint("It's wrong translate!" + "\n" + " Please press next that you can get next word.");
                checkWord = true;
                counter.changeCount(currentWord, -1, 0, 4, db);

            }
        } else {

            if (i < list.getListLearningWords().size()) {

                currentWord = list.getListLearningWords().get(i);
                appointTranslate(currentWord);
                i++;
                checkWord = false;
                editText.setHint("write here translate this word");

            } else {

                list.getListLearningWords().clear();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();

            }
        }
    }

    public void getBackOnMainPageFromLNW(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void appointTranslate(Word word) {
        int count = word.getCount();

        if ((count == 0) || (count == 2)) {
            wordForView = word.getRussianTranslate();
            TextView textView = findViewById(R.id.textViewLearnNewWord);
            textView.setText(wordForView);

            wordForTranslate = word.getEnglishTranslate();
        } else if ((count == 3) || (count == 1)) {
            wordForView = word.getEnglishTranslate();
            TextView textView = findViewById(R.id.textViewLearnNewWord);
            textView.setText(wordForView);

            wordForTranslate = word.getRussianTranslate();
        } else {

            db.insertWord(word.getEnglishTranslate(), word.getRussianTranslate(), 0);
            db.deletedWord(word);

        }
    }
}