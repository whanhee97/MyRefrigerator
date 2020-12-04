package com.example.myrefrigerator;

import android.os.Parcel;
import android.os.Parcelable;

public class TodoItem implements Parcelable {
    int id;
    String memo;
    int checked;

    public TodoItem(){
    }
    protected TodoItem(Parcel in) {
        memo = in.readString();
        checked = in.readInt();
    }

    public static final Creator<TodoItem> CREATOR = new Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel in) {
            return new TodoItem(in);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(memo);
        dest.writeInt(checked);
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
