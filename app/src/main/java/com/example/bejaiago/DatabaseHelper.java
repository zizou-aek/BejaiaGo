package com.example.bejaiago;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String database_Name="Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, database_Name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase Mydatabase) {
        Mydatabase.execSQL("create table allusers (id text primary key,password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydatabase, int i, int i1) {
        Mydatabase.execSQL("drop table if exists allusers");
    }

    public boolean insertdata(String id,String password){
        SQLiteDatabase Mydatabase =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("password",password);
        long result=Mydatabase.insert("allusers",null,contentValues);
        if(result==-1){
            return false;
        }else return true;

    }
    public boolean Chekid(String id){
        SQLiteDatabase Mydatabase=this.getWritableDatabase();
        Cursor cursor=Mydatabase.rawQuery("select from * allusers WHERE id=?",new String[]{id});
        if (cursor.getCount() > 0){
            return true;
        }else return false;
    }

    public boolean Checkidpassword(String id,String password){
        SQLiteDatabase Mydatabase=this.getWritableDatabase();
        Cursor cursor =Mydatabase.rawQuery("select * from allusers WHERE id=? AND password=?",new String[]{id,password});
        if (cursor.getCount()>0){
            return true;
        }else return false;
    }

}
