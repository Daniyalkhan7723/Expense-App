package com.sis.expensiveaapp.DATABSE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.sis.expensiveaapp.Model.Pojo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final String DATABASE_NAME="EXPENSES";
    private static final int DATABASE_VERSION=1;
    //Table name
    private static final String TABLE1="table1";
    private static final String TABLE2="table2";
    //ATTRIBUTES
    private static final String  KEY_ID="id";
    private static final String  KEY_DATE="date";
    private static final String  KEY_TITLE="title";
    private static final String  KEY_DESCRIPTION="description";
    private static final String  KEY_AMOUNT="amount";
    //Constructer
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String table1="CREATE TABLE  " +TABLE1+  "( " +KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_AMOUNT+ " INTEGER Not Null,"
                +KEY_DATE+ " Text Not Null,"
                +KEY_DESCRIPTION+ " Text Not Null)";

        String table2="CREATE TABLE  " +TABLE2+  "( " +KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_AMOUNT+ " INTEGER Not Null,"
                +KEY_DATE+ " Text Not Null,"
                +KEY_DESCRIPTION+ " Text Not Null)";

        db.execSQL(table1);
        db.execSQL(table2);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE1);
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE2);
        onCreate(db);
    }
    public long DataInsertIncome(Pojo pojo) {
        //permisssion
        db = this.getWritableDatabase();
      //  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Content is used to insert or update data into database
        ContentValues c1 =new ContentValues();
        c1.put(KEY_AMOUNT,pojo.getAmount());
        c1.put(KEY_DATE,pojo.getDate());
        c1.put(KEY_DESCRIPTION,pojo.getDescription());
        return  db.insert(TABLE1,null,c1);
    }
    public long DataInsertSaving(Pojo pojo) {
        db = this.getWritableDatabase();
        ContentValues c2 =new ContentValues();
       // SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c2.put(KEY_AMOUNT,pojo.getAmount());
        c2.put(KEY_DATE,pojo.getDate());
        c2.put(KEY_DESCRIPTION,pojo.getDescription());
       return db.insert(TABLE2,null,c2);
    }
    public Cursor viewIncomeData() {
        db=this.getReadableDatabase();
        Cursor c= db.rawQuery("SELECT * from " +TABLE1 , null);
        return c;
    }
    public Cursor viewSavingData() {
        db=this.getReadableDatabase();
        Cursor c= db.rawQuery("SELECT * from " +TABLE2 , null);
        return c;
    }
    public List<Pojo> ViewIncomwData() {
        List<Pojo> pojoArrayList=new ArrayList<>();
        db=this.getReadableDatabase();
        Cursor c= db.rawQuery("SELECT * from " +TABLE1 , null);
        if (c.moveToFirst()){
            do {

                int amount=c.getInt(1);
                String date=c.getString(2);
                String discription=c.getString(3);
                Pojo pojo=new Pojo(amount,discription,date);
                pojoArrayList.add(pojo);
            }
               while (c.moveToNext());
        }
        return pojoArrayList;
    }
    public List<Pojo> ViewSavingData() {
        List<Pojo> pojoArrayList=new ArrayList<>();
        db=this.getReadableDatabase();
        Cursor c= db.rawQuery("SELECT * from " +TABLE2 , null);
        if (c.moveToFirst()){
            do {
                int amount=c.getInt(1);
                String date=c.getString(2);
                String discription=c.getString(3);
                Pojo pojo=new Pojo(amount,discription,date);
                pojoArrayList.add(pojo);
            }
            while (c.moveToNext());
        }
        return pojoArrayList;
    }

//    public int updateIncome(Pojo pojo1) {
//        db=this.getWritableDatabase();
//        ContentValues values=new ContentValues();
//        values.put(KEY_AMOUNT,pojo1.getAmount());
//        values.put(KEY_DATE,pojo1.getDate());
//        values.put(KEY_DESCRIPTION,pojo1.getDescription());
//        return db.update(TABLE1, values,"id=?", new String[]{String.valueOf(Pojo.getId())});
//        return
//    }

    public int deleteIncome(int id) {
        db=this.getWritableDatabase();
        return db.delete("table1","id=?", new String[]{String.valueOf(id) });
    }

    public int updateExpense(Pojo pojo) {
        db=this.getWritableDatabase();
        ContentValues c1 =new ContentValues();
        c1.put(KEY_AMOUNT,pojo.getAmount());
        c1.put(KEY_DATE,pojo.getDate());
        c1.put(KEY_DESCRIPTION,pojo.getDescription());
       return db.update("table1",c1,"id=?",new String[]{String.valueOf(pojo.getId())});
    }
}
