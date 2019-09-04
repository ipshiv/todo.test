package com.example.android.activityscenetransitionbasic.repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.android.activityscenetransitionbasic.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    private static ItemRepository instance;
    private ArrayList<Item> dataset = new ArrayList<>();

    public static ItemRepository getInstance() {
        if (instance == null) {
            instance = new ItemRepository();
        }

        return instance;
    }

    public MutableLiveData<List<Item>> getItems() {

        MutableLiveData<List<Item>> data = new MutableLiveData<>();
        data.setValue(dataset);

        return data;
    }

    private void setItems(ArrayList<Item> items) {
        dataset = items;
    }
}