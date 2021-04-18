package en.engilish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import en.engilish.dao.DBHelper;
import en.engilish.entity.LearningWords;
import en.engilish.entity.Word;

public class LearnNewWord extends AppCompatActivity {

    private DBHelper dbHelper;

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

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_LNW, null, null, null, null, null, null);

        list = LearningWords.getInstance();

        int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
        int ruIndex = cursor.getColumnIndex(DBHelper.KEY_RU);
        int engIndex = cursor.getColumnIndex(DBHelper.KEY_ENG);
        int countIndex = cursor.getColumnIndex(DBHelper.KEY_COUNT_LNW);
        cursor.moveToFirst();
        while (cursor != null && !cursor.isAfterLast()) {
            list.getListLearningWords().add(new Word(
                    cursor.getInt(idIndex),
                    cursor.getString(ruIndex),
                    cursor.getString(engIndex),
                    cursor.getInt(countIndex)
            ));
            cursor.moveToNext();
        }
        cursor.close();
        dbHelper.close();

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

                textView.setText(wordForView + "\n" + wordForTranslate );
                editText.getText().clear();
                editText.setHint("it's true!" + "\n" + " Please press next that you can get next word.");
                checkWord = true;
                changeCountLNW(currentWord, 1);

            } else if (string.isEmpty()) {

                textView.setText(wordForView + "\n" + wordForTranslate);
                editText.getText().clear();
                editText.setHint("Field is empty!" + "\n" + " Please press next that you can get next word.");
                checkWord = true;
                changeCountLNW(currentWord, -1);

            } else {

                textView.setText(wordForView + "\n" + wordForTranslate);
                editText.getText().clear();
                editText.setHint("It's wrong translate!" + "\n" + " Please press next that you can get next word.");
                checkWord = true;
                changeCountLNW(currentWord, -1);

            }
        } else {

            if (i < list.getListLearningWords().size()) {

                currentWord = list.getListLearningWords().get(i);
                appointTranslate(currentWord);
                i++;
                checkWord = false;
                editText.setHint("write here translate this word");

            } else {

                list = null;
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();

            }
        }
    }

    private void appointTranslate(Word word) {

        int count = word.getCountLearnNewWord();

        if ((count == 0) || (count == 2)) {
            wordForView = word.getRussianTranslate();
            TextView textView = findViewById(R.id.textViewLearnNewWord);
            textView.setText(wordForView);

            wordForTranslate = word.getEnglishTranslate();
        } else {
            wordForView = word.getEnglishTranslate();
            TextView textView = findViewById(R.id.textViewLearnNewWord);
            textView.setText(wordForView);

            wordForTranslate = word.getRussianTranslate();
        }
    }

    private void changeCountLNW(Word word, int i) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        int sum = word.getCountLearnNewWord() + i;

        if (sum == 4) {
            contentValues.put(DBHelper.KEY_ENG, word.getEnglishTranslate());
            contentValues.put(DBHelper.KEY_RU, word.getRussianTranslate());
            contentValues.put(DBHelper.KEY_COUNT_CAW, 4);

            db.insert(DBHelper.TABLE_CAW, null, contentValues);

            db.delete(DBHelper.TABLE_LNW, DBHelper.KEY_ID + "=" + word.getId(), null);
            dbHelper.close();
        } else if (sum >= 0) {
            contentValues.put(DBHelper.KEY_COUNT_LNW, sum);
            db.update(DBHelper.TABLE_LNW, contentValues, DBHelper.KEY_ID + "=" + word.getId(), null);
            dbHelper.close();
        }
    }


}