package com.example.doancuoiky.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.doancuoiky.Dialog.NewClassDialog;
import com.example.doancuoiky.model.Classes;

import java.util.ArrayList;
import java.util.List;

public class ClassesDAO {
    private SQLiteDatabase db;

    public ClassesDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        this.db = helper.getWritableDatabase();

    }

    public ClassesDAO(NewClassDialog newClassDialog) {
    }

    public long insert(Classes emp){
        ContentValues values = new ContentValues();
        values.put("id",emp.getId());
        values.put("name",emp.getName());


        return db.insert("classes",null,values);
    }

    @SuppressLint("Range")
    public List<Classes> get(String sql, String ... selectArgs){
        List<Classes> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()){
            Classes cls = new Classes();
            cls.setId(cursor.getInt((cursor.getColumnIndex("id"))));
            cls.setName(cursor.getString(cursor.getColumnIndex("name")));
            list.add(cls);
        }
        return list;
    }
    public List<Classes> getAll(){
        String sql  = "SELECT * FROM classes";

        return get(sql);
    }

    public int delete(String id){
        return db.delete("classes", "id=?",new String[]{id});

    }
}
