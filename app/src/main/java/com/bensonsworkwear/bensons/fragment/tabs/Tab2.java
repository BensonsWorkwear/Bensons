package com.bensonsworkwear.bensons.fragment.tabs;

import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bensonsworkwear.bensons.R;

public class Tab2 extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            /*
             * I was getting a warning for NullPointerException despite
             * being inside a try/catch block.
             * Not anymore.
             */
            @SuppressWarnings("ConstantConditions")
            Button button = getView().findViewById(R.id.pick_colour);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                /* Show color picker dialog */
                    //cp.show();
                }
            });
        } catch (NullPointerException e) {
            Log.e("Button not found", String.valueOf(e));
        }
    }
}
