package com.example.android.activityscenetransitionbasic.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.activityscenetransitionbasic.model.Item;
import com.example.android.activityscenetransitionbasic.model.Storage;
import com.example.android.activityscenetransitionbasic.repository.ItemRepository;
import com.example.android.activityscenetransitionbasic.service.DAO.StorageDAO;

import java.util.List;

public class StorageViewModel extends ViewModel {

    private MutableLiveData<List<Item>> mItems = new MutableLiveData<>();
    private ItemRepository mRepo;
    private Storage mStorage;
    private StorageDAO mDao;

    public void init(Storage storage, StorageDAO dao) {
        if (mItems != null) {
            return;
        }
        // mRepo = ItemRepository.getInstance();
        // mItems = mRepo.getItems();
        mDao = dao;
        mStorage = storage;
        mStorage.setmItems(mDao.fetchItems());
        mItems.setValue(mStorage.getmItems());
    }

    public LiveData<List<Item>> getItemsInStorage() {
        return mItems;
    }

    public void addItem(final Item item) {
        //List<Item> currentItems = mItems.getValue();
        mStorage.addItem(item);
        mDao.addItem(item);
        mItems.postValue(mStorage.getmItems());
    }

}