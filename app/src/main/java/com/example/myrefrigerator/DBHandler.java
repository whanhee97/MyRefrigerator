package com.example.myrefrigerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

public class DBHandler {
    private Context mContext;
    private ArrayList<Food> data;


    public DBHandler(Context context) {
        mContext = context;
    }

    public static DBHandler open(Context context) throws SQLException {

        return new DBHandler(context);

    }

    public long insert(String name, int count, String shelf_life, String icon) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("count", count);
        values.put("shelf_life", shelf_life);
        values.put("icon", icon);
        return DBHelper.getInstance(mContext).insert("tb_food", values);
    }

    public ArrayList<Food> getItems() {

        Cursor cursor = DBHelper.getInstance(mContext).select("tb_food", null, null, null, null, null, null);

        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        data = new ArrayList<Food>();

        while (cursor.moveToNext()) {
            Food vo = new Food();
            vo.setId(cursor.getInt(0));
            vo.setName(cursor.getString(1));
            vo.setCount(cursor.getInt(2));
            vo.setShelf_life(cursor.getString(3));
            vo.setIcon(cursor.getString(4));
            data.add(vo);
        }
        return data;
    }

    public long removeItem(int id) {
        String whereClause = "id" + "=?";
        String[] whereArgs = new String[]{Integer.toString(id)};
        return DBHelper.getInstance(mContext).delete("tb_food", whereClause, whereArgs);
    }

    public int updateCount(int id, int value){
        String whereClause = "id" + "=?";
        String[] whereArgs = new String[]{Integer.toString(id)};
        ContentValues values = new ContentValues();
        values.put("count",value);
        return DBHelper.getInstance(mContext).update("tb_food",values,whereClause,whereArgs);
    }

    public void close() {
        //mHelper.close();
    }
}
