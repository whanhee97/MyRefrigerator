package com.example.myrefrigerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    private static DBHelper _instance;
    private SQLiteDatabase sqLiteDatabase;
    // 생성자 - db파일 생성
    public  DBHelper(Context context){
        super(context,"food.db",null,DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();
    }

    //DB 처음 만들때 한번만 호출 - 테이블 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_food ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "count INTEGER NOT NULL, " +
                "shelf_life TEXT)";
        db.execSQL(sql);
    }
    // 버전이 업데이트되면 DB 다시 만듬
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            db.execSQL("DROP TABLE IF EXISTS tb_food");
            onCreate(db);
        }
    }
    //디비 열어주기
    public static DBHelper getInstance(Context context){
        if(_instance == null){
            _instance = new DBHelper(context);
        }
        if(!_instance.sqLiteDatabase.isOpen()){ // 오류로 닫히면 다시 열어줌
            _instance.sqLiteDatabase = _instance.getWritableDatabase();
        }
        return _instance;
    }
    public long insert(String tableName, ContentValues contentValues){ // 데이터 삽입
        return sqLiteDatabase.insert(tableName,null,contentValues);
    }

    public Cursor select(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return sqLiteDatabase.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public int delete(String table, String whereClause, String[] whereArgs){
        return sqLiteDatabase.delete(table,whereClause,whereArgs);
    }

}
