package com.example.android.activityscenetransitionbasic.service.DAO;

import android.database.sqlite.SQLiteDatabase;

import com.example.android.activityscenetransitionbasic.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SqliteStorageDAO implements StorageDAO {

    private static final String TAG = "SqliteStorageDAO";

    private SQLiteDatabase mDb;
    private String mTableName;

    @Override
    public void init(List<Object> args) {
        this.mDb = (SQLiteDatabase) args.get(0);
        this.mTableName = (String)args.get(1);
        this.mDb.execSQL("CREATE TABLE IF NOT EXISTS " + this.mTableName +
                " (name TEXT, author TEXT, file TEXT, id INTEGER)");

    }

    @Override
    public ArrayList<Item> fetchItems() {
        return null;
    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public void editItemInfo(Item item) {

    }

    @Override
    public void editItemValue(Item item, StorageMethodType method) {

    }

    public void connectToDb() {

    }
}
