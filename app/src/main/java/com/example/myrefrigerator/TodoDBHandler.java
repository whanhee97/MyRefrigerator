package com.example.myrefrigerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

public class TodoDBHandler {
    private Context mContext;
    private ArrayList<TodoItem> data;


    public TodoDBHandler(Context context) {
        mContext = context;
    }

    public static TodoDBHandler open(Context context) throws SQLException {

        return new TodoDBHandler(context);

    }

    public long insert(String memo, int checked) {
        ContentValues values = new ContentValues();
        values.put("memo", memo);
        values.put("checked", checked);
        return TodoDBHelper.getInstance(mContext).insert("tb_todo", values);
    }

    public ArrayList<TodoItem> getItems() {

        Cursor cursor = TodoDBHelper.getInstance(mContext).select("tb_todo", null, null, null, null, null, null);

        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        data = new ArrayList<TodoItem>();

        while (cursor.moveToNext()) {
            TodoItem vo = new TodoItem();
            vo.setId(cursor.getInt(0));
            vo.setMemo(cursor.getString(1));
            vo.setChecked(cursor.getInt(2));
            data.add(vo);
        }
        return data;
    }

    public long removeItem(int id) {
        String whereClause = "id" + "=?";
        String[] whereArgs = new String[]{Integer.toString(id)};
        return TodoDBHelper.getInstance(mContext).delete("tb_todo", whereClause, whereArgs);
    }

    public int updateMemo(int id, String value){
        String whereClause = "id" + "=?";
        String[] whereArgs = new String[]{Integer.toString(id)};
        ContentValues values = new ContentValues();
        values.put("memo",value);
        return TodoDBHelper.getInstance(mContext).update("tb_todo",values,whereClause,whereArgs);
    }

    public int checking(int id, int checked){
        String whereClause = "id" + "=?";
        String[] whereArgs = new String[]{Integer.toString(id)};
        ContentValues values = new ContentValues();
        values.put("checked",checked);
        return TodoDBHelper.getInstance(mContext).update("tb_todo",values,whereClause,whereArgs);
    }

    public void close() {
        //mHelper.close();
    }
}
