package com.example.android.activityscenetransitionbasic.model;

import com.example.android.activityscenetransitionbasic.service.DAO.StorageMethodType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {

    private static final String TAG = "Storage";

    private final String mName, mDescription;
    private final StorageMethodType mMethod;
    private List<Item> mItems = new ArrayList<>();

    public Storage(String mName, String mDescription, StorageMethodType mMethod) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mMethod = mMethod;
    }

    public String getmName() {
        return mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public StorageMethodType getmMethod() {
        return mMethod;
    }

    public List<Item> getmItems() {
        return mItems;
    }

    public void setmItems(ArrayList<Item> mItems) {
        this.mItems = mItems;
    }

    public void removeItem(int pos) {

    }

    public void addItem(Item item) {

    }
}
