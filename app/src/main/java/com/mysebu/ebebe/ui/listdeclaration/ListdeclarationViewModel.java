package com.mysebu.ebebe.ui.listdeclaration;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListdeclarationViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ListdeclarationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is listdeclaration fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}