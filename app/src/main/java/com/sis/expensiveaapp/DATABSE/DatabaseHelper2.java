package com.sis.expensiveaapp.DATABSE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.sis.expensiveaapp.Model.Pojo;
import com.sis.expensiveaapp.Model.Pojo1;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    SQLiteDatabase database;
    private static final String DATABASE_NAME="EXPENSES2.db";
    private static final int DATABASE_VERSION=2;
    //Table name
    private static final String TABLE_3="tabling1";
    //ATTRIBUTES
    private static final String  Col_1="ID";
    private static final String  Col_2="amount";
    private static final String  Col_3="title";
    private static final String  Col_4="description";
    private static final String  Col_5="date";

    //Constructer
    public DatabaseHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  "+TABLE_3+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT , amount INTEGER, title Text , description Text , date Text)");
         }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_3);
        onCreate(db);
    }

    public long DataInsertingLiability(Pojo1 pojo) {
        database = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
       // SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        contentValues.put(Col_2,pojo.getAmount());
        contentValues.put(Col_3,pojo.getTitle());
        contentValues.put(Col_4,pojo.getDescription());
        contentValues.put(Col_5,pojo.getDate());
        return database.insert(TABLE_3,null,contentValues);
    }
    public Cursor viewLiabilityData() {
        database =this.getReadableDatabase();
        Cursor c= database.rawQuery("SELECT * from " +TABLE_3 , null);
        return c;
    }
    public List<Pojo1> ViewDataLiability() {
        List<Pojo1> pojoList1=new ArrayList<>();
        database =this.getReadableDatabase();
        Cursor c= database.rawQuery("SELECT * from " +TABLE_3 , null);
        if (c.moveToFirst()){
            do {
                int amount=c.getInt(1);
              //  String Tile=c.getString(2);
                String discription=c.getString(3);
                String date=c.getString(4);
                Pojo1 pojo2=new Pojo1(amount,discription,date);
                pojoList1.add(pojo2);
            }
            while (c.moveToNext());
        }
        return pojoList1;
    }

    public int deleteLiability(int id) {
        database =this.getWritableDatabase();
        return database.delete(TABLE_3,"id=?", new String[]{String.valueOf(id)});

    }


//    public int updateExpense(Pojo1 pojo) {
//
//
//    }

    public long updateLia(Pojo1 pojo) {
        database =this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(Col_2,pojo.getAmount());
        contentValues.put(Col_3,pojo.getTitle());
        contentValues.put(Col_4,pojo.getDescription());
        contentValues.put(Col_5,pojo.getDate());
        return database.update("tabling1",contentValues,"id=?",new String[]{String.valueOf(pojo.getId())});

    }
}
