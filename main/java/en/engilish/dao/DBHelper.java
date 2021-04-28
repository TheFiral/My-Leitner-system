package en.engilish.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "wordDB";
    public static final String TABLE_LNW = "lnw_table";
    public static final String TABLE_CAW = "caw_table";

    public static final String KEY_ID = "_id";
    public static final String KEY_RU = "ru";
    public static final String KEY_ENG = "eng";
    public static final String KEY_COUNT_LNW = "count_lnw";
    public static final String KEY_COUNT_CAW = "count_caw";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_LNW + "(" + KEY_ID + " integer primary key," + KEY_RU + " text,"
                + KEY_ENG + " text," + KEY_COUNT_LNW + " integer" + ") ");
        db.execSQL("create table " + TABLE_CAW + "(" + KEY_ID + " integer primary key," + KEY_RU + " text,"
                + KEY_ENG + " text," + KEY_COUNT_CAW + " integer" + ") ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
