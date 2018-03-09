package com.bensonsworkwear.bensons.model;

public class NavigationData {

    private boolean isSelected;
    private String name;
    private int drawableId;

    /**
     * TODO: Investigar que hace esto
     * Returns
     *
     * @return <code>True</code> if the element is selected
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Sets if the element is selected
     *
     * @param selected Boolean value of the selection
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Returns the id of the drawable item
     *
     * @return The id of the drawable item
     */
    public int getDrawableId() {
        return drawableId;
    }

    /**
     * Sets the value of the drawable if
     *
     * @param drawableId the drawable id
     */
    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    /**
     * Returns the name of the navigation
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the navigation
     *
     * @param name the name of the navigation
     */
    public void setName(String name) {
        this.name = name;
    }
}

