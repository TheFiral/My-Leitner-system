package en.engilish.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import en.engilish.entity.Word;

import static en.engilish.activity.MainActivity.dbHelper;

public class WordsCAWImpl implements WordsRepo {
    @Override
    public List getAllWords() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_CAW, null, null, null, null, null, null);

        List<Word> list = new ArrayList<>();

        int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
        int ruIndex = cursor.getColumnIndex(DBHelper.KEY_RU);
        int engIndex = cursor.getColumnIndex(DBHelper.KEY_ENG);
        int countIndex = cursor.getColumnIndex(DBHelper.KEY_COUNT_CAW);

        cursor.moveToFirst();
        while (cursor != null && !cursor.isAfterLast()) {
            list.add(new Word(
                    cursor.getInt(idIndex),
                    cursor.getString(ruIndex),
                    cursor.getString(engIndex),
                    cursor.getInt(countIndex)
            ));
            cursor.moveToNext();
        }
        cursor.close();
        dbHelper.close();
        return list;
    }

    @Override
    public void insertWord(String eng, String ru, int count) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_ENG, eng);
        contentValues.put(DBHelper.KEY_RU, ru);
        contentValues.put(DBHelper.KEY_COUNT_LNW, count);

        database.insert(DBHelper.TABLE_LNW, null, contentValues);
        dbHelper.close();
    }

    @Override
    public void deletedWord(Word word) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_CAW, DBHelper.KEY_ID + "=" + word.getId(), null);
        dbHelper.close();
    }

    @Override
    public void updateWord(Word word, int sum) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_COUNT_CAW, sum);
        db.update(DBHelper.TABLE_CAW, contentValues, DBHelper.KEY_ID + "=" + word.getId(), null);
        dbHelper.close();
    }

}
