package com.bibek.foodmandu.ui.home.Model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {



    static List<HomeViewModel> listcategory=new ArrayList<>();
    private int img;

    public HomeViewModel(int img) {
        this.img = img;
    }

    public static List<HomeViewModel> getListcategory() {
        return listcategory;
    }

    public static void setListcategory(List<HomeViewModel> listcategory) {
        HomeViewModel.listcategory = listcategory;
    }

    public int getImg() {
        return img;
    }

}