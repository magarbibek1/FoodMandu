package com.bibek.foodmandu.ui.home.Model;

import java.util.ArrayList;
import java.util.List;

public class FlavoursViewModel {

    static List<FlavoursViewModel> flavoursList=new ArrayList<>();
    private int image;
    private String item;
    private String cafe;

    public FlavoursViewModel(int image, String item, String cafe) {
        this.image = image;
        this.item = item;
        this.cafe = cafe;
    }

    public static List<FlavoursViewModel> getFlavoursList() {
        return flavoursList;
    }

    public static void setFlavoursList(List<FlavoursViewModel> flavoursList) {
        FlavoursViewModel.flavoursList = flavoursList;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCafe() {
        return cafe;
    }

    public void setCafe(String cafe) {
        this.cafe = cafe;
    }
}
