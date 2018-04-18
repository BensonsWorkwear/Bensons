package com.bensonsworkwear.bensons.selection;

import android.app.Dialog;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.TextView;

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

        myDialog = new Dialog(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

        cp.show();

        /* Set a new Listener called when user click "select" */
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
                Log.d("#Hex with alpha", String.format("#%08X", (color)));
            }
        });

/*        new ShowcaseView.Builder(this)
                .setTarget(new ActionViewTarget(this, ActionViewTarget.Type.HOME))
                .setContentTitle("ShowcaseView")
                .setContentText("This is highlighting the Home button")
                .hideOnTouchOutside()
                .build();
                */
    }

    public void showPopup(View v) {
        TextView txtclose;
        Button btnFinish;

        myDialog.setContentView(R.layout.fragment_packaging);
        txtclose = myDialog.findViewById(R.id.txtclose);

        txtclose.setText("M");

        btnFinish = myDialog.findViewById(R.id.finish);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Replace code to actually send the pdf
                myDialog.dismiss();
            }
        });

        Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
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

        @Override
        public Fragment getItem(int position) {
            // getItem is called to return the current page.
            switch (position) {
                case 0:
                    return new Tab1();
                case 1:
                    return new Tab2();
                case 2:
                    return new Tab3();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        Fragment setTab(String className) {
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
        //It should change the name of the tabs but it doesn't work.
        @Override
        public CharSequence getPageTitle(int position) {
            //Returns the name of the title based on the position
            switch (position) {
                case 0:
                    return "Tabla 1";
                case 1:
                    return "Tabla 2";
                case 2:
                    return "Tabla 3";
                default:
                    return null;
            }
        }
        */
    }
}
