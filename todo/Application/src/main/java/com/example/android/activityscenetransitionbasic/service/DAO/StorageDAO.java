package com.example.android.activityscenetransitionbasic.service.DAO;

import com.example.android.activityscenetransitionbasic.model.Item;

import java.util.ArrayList;
import java.util.List;

public interface StorageDAO {

    public abstract  void init(List<Object> args);
    public abstract ArrayList<Item> fetchItems();
    public abstract void addItem(Item item);
    public abstract void editItemInfo(Item item);
    public abstract void editItemValue(Item item, StorageMethodType method);
}
