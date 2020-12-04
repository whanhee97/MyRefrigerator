package com.example.myrefrigerator;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private int id;
    private String name;
    private int count;
    private String shelf_life;
    private String icon;

    protected Food(Parcel in) {
        id = in.readInt();
        name = in.readString();
        count = in.readInt();
        shelf_life = in.readString();
        icon = in.readString();
    }
    public Food(){
    }
    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getShelf_life() {
        return shelf_life;
    }

    public void setShelf_life(String shelf_life) {
        this.shelf_life = shelf_life;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(count);
        dest.writeString(shelf_life);
        dest.writeString(icon);
    }
}
