package com.example.myrefrigerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {

    private static Context context;
    TodoDBHandler todoDBHandler = null;
    ListView listView;
    ArrayList<TodoItem> memoList;
    TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);


        todoDBHandler = new TodoDBHandler(this);
        memoList = todoDBHandler.getItems();

        listView = (ListView)findViewById(R.id.listView_memo);

        todoDBHandler = new TodoDBHandler(this);


    }


    @Override
    public void onResume() {
        super.onResume();

        todoAdapter = new TodoAdapter(this,memoList);
        if(memoList!=null)
            listView.setAdapter(todoAdapter);
        else{
            listView.setAdapter(null);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sub_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.gotohome:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mOnClick(View v){
        int count;

        SparseBooleanArray checkedItems;
        switch (v.getId()){
            case R.id.btn_add_memo:
                CustomDialogFragment customDialog = CustomDialogFragment.newInstance(new CustomDialogFragment.MemoInputListener() {
                    @Override
                    public void onMemoInputComplete(String memo) {
                        if(memo != null){
                            todoDBHandler.insert(memo,0);
                            memoList = todoDBHandler.getItems();
                            onResume();
                        }
                    }
                });
                customDialog.show(getSupportFragmentManager(),"addDialog");

                break;
            case R.id.btn_delete_checkedmemo:
                checkedItems = listView.getCheckedItemPositions();
                if(checkedItems.size() == 0){
                    break;
                }
                count = todoAdapter.getCount();
                for(int i = count-1;i>=0;i--){
                    if(checkedItems.get(i)){
                        todoDBHandler.removeItem(memoList.get(i).getId());
                    }
                }
                memoList = todoDBHandler.getItems();
                onResume();
                break;
            case R.id.btn_delete_memo:
                count = todoAdapter.getCount();
                for(int i =0;i<count;i++){
                    todoDBHandler.removeItem(memoList.get(i).getId());
                }
                memoList = todoDBHandler.getItems();
                onResume();
                break;
            case R.id.btn_hold_check:
                count = todoAdapter.getCount();
                checkedItems = listView.getCheckedItemPositions();
                for(int i = count-1;i>=0;i--){
                    if(checkedItems.get(i)){
                        if(memoList.get(i).getChecked() == 1){
                            todoDBHandler.checking(memoList.get(i).getId(),0);
                        }else{
                            todoDBHandler.checking(memoList.get(i).getId(),1);
                        }

                    }
                }
                memoList = todoDBHandler.getItems();
                onResume();
                break;
        }
    }

}