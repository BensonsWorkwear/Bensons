package com.bensonsworkwear.bensons.selection;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bensonsworkwear.bensons.R;
import com.bensonsworkwear.bensons.fragment.tabs.CapFragment;
import com.bensonsworkwear.bensons.fragment.tabs.JacketFragment;
import com.bensonsworkwear.bensons.fragment.tabs.MaterialFragment;
import com.bensonsworkwear.bensons.fragment.tabs.PrintFragment;
import com.bensonsworkwear.bensons.fragment.tabs.TShirtFragment;
import com.bensonsworkwear.bensons.fragment.tabs.Tab1;
import com.bensonsworkwear.bensons.fragment.tabs.Tab2;
import com.bensonsworkwear.bensons.fragment.tabs.Tab3;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import java.util.Objects;

public class Selection extends AppCompatActivity {

    public ColorPicker cp;
    Dialog myDialog;
    ImageView preview;
    EditText quantity;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    protected SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    protected ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        quantity = findViewById(R.id.quantity);

        myDialog = new Dialog(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        cp = new ColorPicker(this, 255, 255, 255, 255);

        /* Sets a new Listener called when user click "select" */
        cp.setCallback(new ColorPickerCallback() {

            @Override
            public void onColorChosen(@ColorInt int color) {
                // Do whatever you want
                // Examples
                Log.d("Alpha", Integer.toString(Color.alpha(color)));
                Log.d("Red", Integer.toString(Color.red(color)));
                Log.d("Green", Integer.toString(Color.green(color)));
                Log.d("Blue", Integer.toString(Color.blue(color)));

                Log.d("Pure Hex", Integer.toHexString(color));
                Log.d("#Hex no alpha", String.format("#%06X", (0xFFFFFF & color)));
                Log.d("#Hex with alpha", String.format("#%08X", color));

                // Applies a color filter to the image with the selected hex value
                preview = findViewById(R.id.preview_image);

                preview.getDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);

                cp.dismiss();
            }
        });
    }

    /**
     * Opens a popup with a hex color picker.
     *
     * @param v The view
     */
    public void showColorPicker(View v) {
        cp.show();
    }

    /**
     * Opens a popup with the finish options.
     *
     * @param v The view
     */
    public void onClickFinish(View v) {
        TextView txtClose;

        //validateQuantity();

        myDialog.setContentView(R.layout.popup_packaging);
        txtClose = myDialog.findViewById(R.id.txtclose);

        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    //TODO: This validation function works but it's not called
    //TODO: It should receive the TextBox to validate as a parameter

    /**
     * Validates the garment quantity
     */
    public void validateQuantity() {
        //Checks if the TextBox is empty before validating.
        if (quantity.getText() != null) {
            if (quantity.getText().toString().trim().length() > 0) {
                Toast.makeText(getApplicationContext(), "The quantity can't be empty", Toast.LENGTH_LONG).show();
            } else if (Integer.parseInt(quantity.getText().toString()) < 300) {
                Toast.makeText(getApplicationContext(), "Minimum order of 300 units", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "The quantity can't be empty", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Deleted placeHolderFragment class was deleted from here

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //TODO: Make this not static, it should load different fragments depending on the selected RadioButton on the garmentPopup
        @Override
        public Fragment getItem(int position) {
            // getItem is called to return the current page.
            switch (position) {
                case 0:
                    return launchTab("Tab1");
                case 1:
                    return launchTab("Tab2");
                case 2:
                    return launchTab("Tab3");
                default:
                    return null;
            }
        }

        /**
         * This is the amount of tabs and there is no need to change it
         *
         * @return The amount of tabs
         */
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        /* I have another similar piece of code somewhere else
         * the idea is that depending on what you select in the garmentPopup
         * it will launch different tabs
         */
        private Fragment launchTab(String className) {
            switch (className) {
                case "CapFragment":
                    return new CapFragment();
                case "JacketFragment":
                    return new JacketFragment();
                case "MaterialFragment":
                    return new MaterialFragment();
                case "PrintFragment":
                    return new PrintFragment();
                case "Tab1":
                    return new Tab1();
                case "Tab2":
                    return new Tab2();
                case "Tab3":
                    return new Tab3();
                case "TShirtFragment":
                    return new TShirtFragment();
                default:
                    return new Tab1();
            }
        }

        /*
         * This should change the name of the tabs but it doesn't work.
         * It's commented out just in case, it doesn't crash or do
         * anything for that matter, but I don't want to risk it.
         */
        /*
        @Override
        public CharSequence getPageTitle(int position) {
            //Returns the name of the title based on the position
            switch (position) {
                case 0:
                    return "Test 1";
                case 1:
                    return "Test 2";
                case 2:
                    return "Test 3";
                default:
                    return null;
            }
        }
        */
    }
}
