package com.bensonsworkwear.bensons.adapter;

public interface INavigation {

    /**
     * Interface for the selected element in the Navigation Drawer.
     * The position is the index number of the selected element in the navigation drawer.
     *
     * @param position Position of the pressed element
     */
    void onViewClick(int position);

    /**
     * Interface for the selected image in the Navigation Drawer.
     * The position is the index number of the selected element in the navigation drawer.
     *
     * @param position Position of the pressed image
     */
    void onIconClick(int position);
}

