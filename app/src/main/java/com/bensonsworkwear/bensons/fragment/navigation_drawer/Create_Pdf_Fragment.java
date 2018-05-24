package com.bensonsworkwear.bensons.fragment.navigation_drawer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bensonsworkwear.bensons.R;

import butterknife.ButterKnife;

public class Create_Pdf_Fragment extends Fragment {

    public static Create_Pdf_Fragment newInstance() {
        return new Create_Pdf_Fragment();
    }

    /**
     * @param inflater The {@link LayoutInflater}
     * @param container The container where the {@link Fragment} is going to be
     * @param savedInstanceState The instance saved if there is any
     * @return The current {@link View}
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_pdf, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}

