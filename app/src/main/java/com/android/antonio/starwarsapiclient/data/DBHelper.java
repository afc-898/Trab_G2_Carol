package com.android.antonio.starwarsapiclient.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private final static int VERSION = 1;
    private static String createLastUsesTable = "CREATE TABLE lastuses(id VARCHAR(20) primary key, name VARCHAR(100), type VARCHAR(30), api_id INTEGER, update_millis INTEGER)";

    public DBHelper(Context context) {
        super(context, "dblegalzao", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createLastUsesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
