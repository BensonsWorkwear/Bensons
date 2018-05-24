package com.bensonsworkwear.bensons.popups;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.Toast;

import com.bensonsworkwear.bensons.R;

public class GarmentPopup extends AppCompatActivity {

    ConstraintLayout layout;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_garment);

        layout = findViewById(R.id.layout);

        rg = findViewById(R.id.radioGroup);

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

        onCheckedChangedListener(rg);
    }

    //This function does nothing, there is a problem somewhere. Good luck!
    /**
     * Detects if a {@link RadioButton} has been selected
     *
     * @param rg The {@link RadioGroup}
     */
    public void onCheckedChangedListener(RadioGroup rg) {
        TableRow v;
        RadioButton rb;

        for (int i = 0; i < rg.getChildCount(); i++) {
            v = (TableRow) rg.getChildAt(i);

            for (int x = 0; x < v.getChildCount(); x++) {
                rb = (RadioButton) v.getChildAt(x);
                if (rb != null) {
                    View.OnClickListener first_radio_listener = new View.OnClickListener() {
                        public void onClick(View v) {
                            //TODO: Here you should deselect any other RadioButton that was selected except the one that was just selected. You should probably call another function that does that.
                            Toast.makeText(getApplicationContext(), "Change", Toast.LENGTH_LONG).show();
                        }
                    };

                    rb.setOnClickListener(first_radio_listener);
                }
            }
        }
    }

    /*    private String selectedGarment() {
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
        }*/
}
