package com.example.doancuoiky.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "doancuoiky";
    private static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String classSql = "CREATE TABLE classes(id Integer primary key autoincrement," +
                "name  text not null)";
        String studdentSql = "CREATE TABLE students( id text primary key," +
                "name text not null, classid integer, dob text,"+
                "FOREIGN KEY (classid) REFERENCES classes(id)) ";
        String Account ="CREATE TABLE Account(id integer primary key autoincrement," +
                "username text not null," +
                "password text not null," +
                " role text," + "name text)";
        final String Insert_Data="INSERT INTO Account VALUES(1,'admin','admin','0932333263','Vo Dai Thang')";
        sqLiteDatabase.execSQL(classSql);
        sqLiteDatabase.execSQL(studdentSql);
        sqLiteDatabase.execSQL(Account);
        sqLiteDatabase.execSQL(Insert_Data);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String classesSql = "DROP TABLE IF EXISTS classes";
        String studentSql = "DROP TABLE IF EXISTS students";
        String Account ="DROP TABLE IF EXISTS Account";


        sqLiteDatabase.execSQL(studentSql);
        sqLiteDatabase.execSQL(classesSql);
        sqLiteDatabase.execSQL(Account);

        onCreate(sqLiteDatabase);

    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * from Account ",null);
        return cursor;
    }
}
