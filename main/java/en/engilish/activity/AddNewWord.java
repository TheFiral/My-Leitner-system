package en.engilish.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import en.engilish.R;
import en.engilish.dao.WordsCAWImpl;
import en.engilish.dao.WordsLNWImpl;
import en.engilish.dao.WordsRepo;

public class AddNewWord extends AppCompatActivity {

    private WordsRepo db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);
    }

    public void getNewWord(View view) {

        EditText russian = findViewById(R.id.russianTextField);
        EditText english = findViewById(R.id.englishTextField);
        String ru = russian.getText().toString();
        String eng = english.getText().toString();

        CheckBox checkBox = findViewById(R.id.checkbox);

        if ((!ru.isEmpty()) && (!eng.isEmpty())) {

            if (checkBox.isChecked()) {
                db = new WordsCAWImpl();
            } else {
                db = new WordsLNWImpl();
            }
            db.insertWord(eng.trim(), ru.trim(), 0);

            russian.getText().clear();
            english.getText().clear();
            russian.setHint("Please write new russian word");
            english.setHint("Please write new english word");

        } else {

            russian.getText().clear();
            english.getText().clear();
            russian.setHint("One of fields are empty");
            english.setHint("One of fields are empty");

        }
    }

    public void getBackOnMainPage(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}