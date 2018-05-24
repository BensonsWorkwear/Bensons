package com.bensonsworkwear.bensons;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.bensonsworkwear.bensons.constants.Constants;
import com.bensonsworkwear.bensons.fragment.navigation_drawer.AboutFragment;
import com.bensonsworkwear.bensons.fragment.navigation_drawer.BaseFragment;
import com.bensonsworkwear.bensons.fragment.navigation_drawer.NavigationFragment;
import com.bensonsworkwear.bensons.fragment.navigation_drawer.ElementFragment;
import com.bensonsworkwear.bensons.fragment.navigation_drawer.SendFragment;
import com.bensonsworkwear.bensons.fragment.navigation_drawer.Create_Pdf_Fragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.view.Gravity.START;

public class NavigationActivity extends AppCompatActivity {

    DrawerLayout drawer;
    Dialog myDialog;
    ImageView preview;
    EditText quantity;
    ConstraintLayout layout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_ivNavigation)
    ImageView toolbar_ivNavigation;
    @BindView(R.id.toolbar_tvTitle)
    TextView tvTitle;

    RadioGroup rg;

    /**
     * No being used currently
     */
    @OnClick(R.id.toolbar_ivNavigation)
    void onClickNavigation() {
        openCloseDrawer();
    }

    Unbinder unbinder;

    /**
     * Runs when the activity is created. Sets the name of the activity, icons and fragments.
     *
     * @param savedInstanceState Value will be null the first time called. Otherwise it will pass the bundle.
     */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        unbinder = ButterKnife.bind(this);
        myDialog = new Dialog(this);
        toolbar = findViewById(R.id.toolbar);
        setToolbarTitle(getString(R.string.title_navigation_activity));
        toolbar.setNavigationIcon(null);
        setSupportActionBar(toolbar);
        rg = myDialog.findViewById(R.id.radioGroup);

        drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //The first fragment is selected by default
        replaceFragment(0);
        replaceNavigationFragment();
    }

    /**
     * Closes the drawer, if it's open it calls the superclass of {@link FragmentActivity}
     */
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(START)) {
            drawer.closeDrawer(START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Opens the drawer, if it's already open it closes it.
     */
    private void openCloseDrawer() {
        if (drawer.isDrawerOpen(START)) drawer.closeDrawer(START);
        else drawer.openDrawer(START);
    }

    /**
     * Replaces the fragment with the flContainerNavigationMenu activity.
     */
    public void replaceNavigationFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainerNavigationMenu, NavigationFragment.newInstance(), "Navigation").commit();
    }

    /**
     * Detects the selected item and sets the activity fragment according to the selection.
     *
     * @param position The position selected.
     */
    public void replaceFragment(int position) {
        Fragment fragment = null;
        String tag = null;

        /* Parameter comes from the element pressed in the navigation drawer.
         * Pressing the first one will give the value 0, which means that the user
         * wants to go to the news section
         */
        switch (position) {
            case 0:
                fragment = BaseFragment.newInstance();
                tag = Constants.TAG_FRG_BASE;
                break;
            case 1:
                fragment = ElementFragment.newInstance();
                tag = Constants.TAG_FRG_ELEMENT;
                break;
            case 2:
                fragment = Create_Pdf_Fragment.newInstance();
                tag = Constants.TAG_FRG_CREATE_PDF;
                break;
            case 3:
                fragment = SendFragment.newInstance();
                tag = Constants.TAG_FRG_SEND;
                break;
            case 4:
                fragment = AboutFragment.newInstance();
                tag = Constants.TAG_FRG_ABOUT;
                break;
            case 5:
                showGarmentPopup();
                break;
        }

        if (position != 5) {
            replaceFragment(fragment, tag);
        }

    }

    /**
     *
     */
    public void showGarmentPopup() {
        TextView txtClose;
        Button accept;

        //validateQuantity();

        myDialog.setContentView(R.layout.popup_garment);
        txtClose = myDialog.findViewById(R.id.txtclose);
        accept = myDialog.findViewById(R.id.btn_accept);

        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* This should be in the Selection.java file
                 * not here. This makes no sense.
                 * Also the case is not doing anything.
                 * TODO: Pressing accept should run the proper activity/fragments
                 * TODO: Add return values
                 */
                switch (selectedRadioButton(rg)) {
                    case "T-Shirt":
                        break;
                    case "Polo":
                        break;
                    case "Jacket":
                        break;
                    case "Trousers":
                        break;
                    case "Vest":
                        break;
                    case "Cap":
                        break;
                    case "Print":
                        break;
                    case "Embroidery":
                        break;
                }

                myDialog.dismiss();
            }
        });

        Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    /**
     * Takes a {@link RadioGroup} and returns the selected {@link RadioButton}. The
     * {@link RadioButton} does not have to be a direct child of the {@link RadioGroup}
     *
     * @param rg The {@link RadioGroup} where the {@link RadioButton}s are.
     * @return The string of the selected {@link RadioButton}
     */
    public String selectedRadioButton(RadioGroup rg) {
        TableRow v;
        RadioButton rb;

        for (int i = 0; i < rg.getChildCount(); i++) {
            v = (TableRow) rg.getChildAt(i);

            for (int x = 0; x < v.getChildCount(); x++) {
                rb = (RadioButton) v.getChildAt(x);
                if (rb != null) {
                    if (rb.isChecked()) {
                        return rb.getText().toString();
                    }
                }
            }
        }

        return null;
    }

    /**
     * Replaces the fragment with another activity. It receives the fragment that wants to be
     * used and then string for the title. To end it closes the navigation drawer activity
     *
     * @param fragment The activity to be replace the fragment.
     * @param tag      The new title of the toolbar.
     */
    public void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContainerFragment, fragment, tag)
                .commit();

        setToolbarTitle(tag);
        closeNavigationDrawer();
    }

    /**
     * Sets the title on the toolbar of the activity. Receives a title of the type {@link String}
     * and replaces the current title.
     *
     * @param title The {@link String} to be set as the title of the activity.
     */
    public void setToolbarTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * If the Drawer is open it closes it.
     */
    public void closeNavigationDrawer() {
        if (drawer.isDrawerOpen(START)) drawer.closeDrawer(START);
    }

    /**
     * Unbinds all views from Butterknife to release memory before closing.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
