package en.engilish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import en.engilish.dao.DBHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activyty_main_page);
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
}