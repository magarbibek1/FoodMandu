package com.bibek.foodmandu.ui.bakeries;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bibek.foodmandu.R;

public class BakeriesActivity extends Fragment {


    private BakeriesViewModel bakeriesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bakeriesViewModel =
                ViewModelProviders.of(this).get(BakeriesViewModel.class);
        View root = inflater.inflate(R.layout.activity_login, container, false);

        return root;
    }

}
