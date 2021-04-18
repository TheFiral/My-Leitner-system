package en.engilish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import en.engilish.dao.DBHelper;

public class AddNewWord extends AppCompatActivity {


    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        dbHelper = new DBHelper(this);
    }

    public void getNewWord(View view) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        EditText russian = findViewById(R.id.russianTextField);
        EditText english = findViewById(R.id.englishTextField);
        String ru = russian.getText().toString().trim();
        String eng = english.getText().toString().trim();
        if ((!ru.isEmpty()) && (!eng.isEmpty())) {

            contentValues.put(DBHelper.KEY_ENG, eng);
            contentValues.put(DBHelper.KEY_RU, ru);
            contentValues.put(DBHelper.KEY_COUNT_LNW, 0);

            database.insert(DBHelper.TABLE_LNW, null, contentValues);
            dbHelper.close();

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