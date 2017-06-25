package com.android.antonio.starwarsapiclient.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;



public class LastUsesDAO {
    DBHelper dbHelper;

    public LastUsesDAO(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public List<LastUse> getAll(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<LastUse> result = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM lastuses ORDER BY update_millis DESC",null);
        if(cursor.moveToFirst()){
            do{
                try {

                    LastUse lastUse = new LastUse();
                    lastUse.setId(cursor.getString(cursor.getColumnIndex("id")));
                    lastUse.setApiId(cursor.getInt(cursor.getColumnIndex("api_id")));
                    lastUse.setName(cursor.getString(cursor.getColumnIndex("name")));
                    lastUse.setType(cursor.getString(cursor.getColumnIndex("type")));
                    result.add(lastUse);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }while (cursor.moveToNext());
        }
        return result;
    }

    public void save(LastUse lastUse){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",lastUse.getId());
        values.put("api_id",lastUse.getApiId());
        values.put("name",lastUse.getName());
        values.put("type",lastUse.getType());
        values.put("update_millis",System.currentTimeMillis());
        try {
            db.insertOrThrow("lastuses",null,values);
        }catch (Exception e){
            db.update("lastuses",values,"id = ?", new String[]{lastUse.getId()});
        }
    }


}
