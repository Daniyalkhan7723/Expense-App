package com.sis.expensiveaapp.DATABSE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.sis.expensiveaapp.Model.Pojo1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper3 extends SQLiteOpenHelper {

    SQLiteDatabase db2;
    private static final String DATABASE_NAME="EXPENSES3.db";
    private static final int DATABASE_VERSION=3;
    //Table name
    private static final String TABLE_4="tabling2";
    //ATTRIBUTES
    private static final String  Col_1="ID";
    private static final String  Col_2="amount";
    private static final String  Col_3="title";
    private static final String  Col_4="description";
    //Constructer
    public DatabaseHelper3(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  "+TABLE_4+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT , amount INTEGER, title Text , description Text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_4);
        onCreate(db);
    }


    public long DataInsertingAssets(Pojo1 pojo) {
        db2 = this.getWritableDatabase();
        ContentValues contentValues1 =new ContentValues();
        contentValues1.put(Col_2,pojo.getAmount());
        contentValues1.put(Col_3,pojo.getTitle());
        contentValues1.put(Col_4,pojo.getDescription());
        return db2.insert(TABLE_4,null,contentValues1);
    }

    public Cursor viewAssetsData() {
        db2 = this.getReadableDatabase();
        Cursor c = db2.rawQuery("SELECT * from " + TABLE_4, null);
        return c;
    }
    public List<Pojo1> ViewDataLiability() {
        List<Pojo1> pojoList2=new ArrayList<>();
        db2=this.getReadableDatabase();
        Cursor c= db2.rawQuery("SELECT * from " +TABLE_4 , null);
        if (c.moveToFirst()){
            do {
                int amount=c.getInt(1);
                //  String Tile=c.getString(2);
                String discription=c.getString(3);
                Pojo1 pojo2=new Pojo1(amount,discription);
                pojoList2.add(pojo2);
            }
            while (c.moveToNext());
        }
        return pojoList2;
    }

}
