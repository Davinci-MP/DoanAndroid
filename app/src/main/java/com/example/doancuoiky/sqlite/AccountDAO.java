package com.example.doancuoiky.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doancuoiky.model.NewAccount;

import java.util.ArrayList;
import java.util.List;

//import com.example.doancuoiky.model.NewDistrict;


public class AccountDAO {
    private SQLiteDatabase db;

    public AccountDAO(Context context) {
        DbHelper helper = new DbHelper(context);

        this.db = helper.getWritableDatabase();
    }
    public long insert(NewAccount emp){
        ContentValues values = new ContentValues();
        values.put("id",emp.getId());
        values.put("username",emp.getUsername());
        values.put("password",emp.getPassword());
        values.put("name", emp.getName());
        values.put("role",emp.getRole());

        return  db.insert("Account",null,values);
    }

    public long update(NewAccount emp){
        ContentValues values = new ContentValues();
        values.put("id",emp.getId());
        values.put("username",emp.getUsername());
        values.put("password",emp.getPassword());
        values.put("name", emp.getName());
        values.put("role",emp.getRole());

        return  db.update("Account",values,
                " id =?",new String[]{emp.getId()});
    }
    public long delete(String id){

        return  db.delete("Account", " id =?",new String[]{id});
    }


    @SuppressLint("Range")
    public List<NewAccount> get(String sql, String ...selectArgs){
        List<NewAccount> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selectArgs);

        while(cursor.moveToNext()){
            NewAccount nac= new NewAccount();
            nac.setId(cursor.getString(cursor.getColumnIndex("id")));
            nac.setName((cursor.getString(cursor.getColumnIndex("name"))));
            nac.setUsername((cursor.getString(cursor.getColumnIndex("username"))));
            nac.setPassword((cursor.getString(cursor.getColumnIndex("password"))));
            nac.setRole((cursor.getString(cursor.getColumnIndex("role"))));
            list.add(nac);
        }
        return list;
    }

    public List<NewAccount> getAll(){
        String sql= "SELECT * FROM Account";

        return  get(sql);
    }

}
