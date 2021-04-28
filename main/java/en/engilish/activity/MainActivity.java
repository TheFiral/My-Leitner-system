package en.engilish.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import en.engilish.R;
import en.engilish.dao.DBHelper;

public class MainActivity extends AppCompatActivity {

    public static DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activyty_main_page);
        dbHelper = new DBHelper(this);
    }

    public void openLearnNewWord(View view) {
        Intent intent = new Intent(this, LearnNewWord.class);
        startActivity(intent);
    }

    public void openCheckAllWord(View view) {
        Intent intent = new Intent(this, CheckAllWord.class);
        startActivity(intent);
    }

    public void openAddNewWord(View view) {
        Intent intent = new Intent(this, AddNewWord.class);
        startActivity(intent);
    }

    public void openEditAllWord(View view){
        Intent intent = new Intent(this, EditAllWords.class);
        startActivity(intent);
    }
}