package com.bibek.foodmandu.ui.bakeries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BakeriesViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public BakeriesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
