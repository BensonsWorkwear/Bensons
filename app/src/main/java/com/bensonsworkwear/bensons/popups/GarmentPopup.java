package com.bensonsworkwear.bensons.popups;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bensonsworkwear.bensons.R;
import com.bensonsworkwear.bensons.selection.Selection;

public class GarmentPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_garment);

        findViewById(R.id.btn_accept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout layout = findViewById(R.id.layout);

                Toast.makeText(getApplicationContext(), String.valueOf(layout.getChildCount()), Toast.LENGTH_LONG).show();

                //selectedGarment();
            }
        });

        findViewById(R.id.txtclose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this.dismiss();
            }
        });
    }

    private String selectedGarment() {
        ConstraintLayout layout = findViewById(R.id.layout);

        //Loops through the view to find RadioButtons
        for (int i = 0; i < layout.getChildCount(); i++) {
            View v = layout.getChildAt(i);

            //If the RadioButton is selected it returns it's id
            if (v instanceof RadioButton) {
                if (((RadioButton) v).isChecked()) {
                    return ((RadioButton) v).getText().toString();
                }
            }
        }
        return null;
    }
}
