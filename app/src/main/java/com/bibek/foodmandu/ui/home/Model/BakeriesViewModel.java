package com.bibek.foodmandu.ui.home.Model;

import java.util.ArrayList;
import java.util.List;

public class BakeriesViewModel {

    static List<BakeriesViewModel> bakeriesList=new ArrayList<>();
    private int image;
    private String name;

    public BakeriesViewModel(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public static List<BakeriesViewModel> getBakeriesList() {
        return bakeriesList;
    }

    public static void setBakeriesList(List<BakeriesViewModel> bakeriesList) {
        BakeriesViewModel.bakeriesList = bakeriesList;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
