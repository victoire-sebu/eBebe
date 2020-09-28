package com.mysebu.ebebe.ui.naissance;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NaissanceViewModel extends ViewModel {


    private MutableLiveData<String> mText;

    public NaissanceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is birth(naissance) fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}