package com.bensonsworkwear.bensons.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bensonsworkwear.bensons.NavigationActivity;
import com.bensonsworkwear.bensons.R;
import com.bensonsworkwear.bensons.adapter.INavigation;
import com.bensonsworkwear.bensons.adapter.NavigationAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationFragment extends Fragment implements INavigation {

    public static final String TAG = NavigationFragment.class.getSimpleName();

    @BindView(R.id.rvNavigation)
    RecyclerView rvNavigation;

    private NavigationAdapter adapter;

    private int array_icons[] = {R.drawable.icon_menu_news, R.drawable.icon_menu_feeds, R.drawable.icon_menu_watchlive, R.drawable.icon_menu_popular_tags, R.drawable.icon_menu_settings, R.drawable.icon_menu_about};

    /**
     * Runs when the activity is launched. The first time savedInstanceState is null.
     *
     * @param savedInstanceState State of the application bundle.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates a new navigation fragment. This navigatin fragment has a new bundle a an argument.
     *
     * @return A new navigation fragment.
     */
    public static NavigationFragment newInstance() {
        NavigationFragment navigationFragment = new NavigationFragment();
        Bundle args = new Bundle();
        navigationFragment.setArguments(args);
        return navigationFragment;
    }

    /**
     * Runs when the view is created. Attaches the view to the root of the container.
     *
     * @param inflater
     * @param container An object that provides a set of LayoutParams values for root of the returned hierarchy.
     * @param savedInstanceState State of the application bundle.
     * @return The root View of the inflated hierarchy. It is the root of the inflated XML file.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        ButterKnife.bind(this, view);
        fillData();
        setAdapter();
        adapter.setSelected(0);
        return view;
    }

    /**
     * Fill the navigation drawer with the fragments. Sets all the drawers for each fragment bu getting their name and navigation from the array in Strings.xml.
     * <p>Inserts into an {@link ArrayList} all the navigationData of the fragments. </p>
     * @return An {@link ArrayList} of the fragments.
     */
    private ArrayList<com.bensonsworkwear.bensons.model.NavigationData> fillData() {
        ArrayList<com.bensonsworkwear.bensons.model.NavigationData> navigationDataArrayList = new ArrayList<>();
        String array_navigation[] = getResources().getStringArray(R.array.array_navigation);

        for (int i = 0; i < array_navigation.length; i++) {
            com.bensonsworkwear.bensons.model.NavigationData navigationData = new com.bensonsworkwear.bensons.model.NavigationData();
            navigationData.setName(array_navigation[i]);
            navigationData.setDrawableId(array_icons[i]);
            navigationDataArrayList.add(navigationData);
        }

        return navigationDataArrayList;
    }

    /**
     * Sets the {@link NavigationAdapter}.
     */
    private void setAdapter() {
        adapter = new NavigationAdapter(this);
        rvNavigation.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNavigation.setAdapter(adapter);

        adapter.refreshAdapter(fillData());
    }

    /**
     * Replaces the fragment with the position of the parameter. It also prints to logcat the view and it's position.
     *
     * @param position Position of the fragment.
     */
    @Override
    public void onViewClick(int position) {
        Log.e(TAG, "View" + position);
        replaceFragment(position);
    }

    /**
     * Replaces the fragment with the position of the parameter. It also prints to logcat the icon and it's position.
     *
     * @param position Position of the pressed image.
     */
    @Override
    public void onIconClick(int position) {
        Log.e(TAG, "Icon" + position);
        replaceFragment(position);
    }

    /**
     * Replaces the fragment with the passed parameter. If the activiy is null it doesn't do anything.
     *
     * @param position Position of the fragment.
     */
    private void replaceFragment(int position) {
        assert (getActivity()) != null;
        ((NavigationActivity) getActivity()).replaceFragment(position);
        adapter.setSelected(position);
    }
}

