package com.bensonsworkwear.bensons.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bensonsworkwear.bensons.R;
import com.bensonsworkwear.bensons.model.NavigationData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    private List<NavigationData> navigationData;
    private INavigation listener;

    /**
     * Initializes the Arraylist and sets the listener.
     *
     * @param listener {@link INavigation} listener
     */
    public NavigationAdapter(INavigation listener) {
        navigationData = new ArrayList<>();
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Nullable
        @BindView(R.id.tvNavigationName)
        TextView tvNavigationName;
        @Nullable
        @BindView(R.id.ivNavigation)
        ImageView ivNavigation;

        /**
         * Currently not used, it gets the tag of the image pressed and sends it to the listener.
         */
        @OnClick(R.id.ivNavigation)
        void onClickIcon() {
            if (ivNavigation != null)
                //noinspection deprecation
                listener.onIconClick(Integer.parseInt(ButterKnife.findById(ivNavigation, R.id.ivNavigation).getTag().toString()));
        }

        /**
         * {@link ViewHolder} describes an item view and metadata about its place within the RecyclerView.
         *
         * @param itemView Current view.
         */
        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        /**
         * Gets the tag of the selected element. After that it sends it to the listener.
         *
         * @param view The current {@link View} of the app.
         */
        @Override
        public void onClick(View view) {
            listener.onViewClick(Integer.parseInt(view.getTag().toString()));
        }
    }

    /**
     * TODO Esto puede estar escrito bastante mejor. No estoy seguro de que hace ni que se llegue a utilizar.
     * I don't think this is used anywhere. I won't delete it until i know what it does.
     *
     * @param parent   The parent of the {@link ViewGroup}
     * @param viewType Type of the view
     * @return The {@link NavigationAdapter} ViewHolder @link ItemView}
     */
    @NonNull
    @Override
    public NavigationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_navigation, parent, false);

        return new NavigationAdapter.ViewHolder(itemView);
    }

    /**
     * Describes an item view and metadata about its place within the {@link RecyclerView}
     *
     * @param holder   The ViewHolder
     * @param position Position in the {@link RecyclerView}
     */
    @Override
    public void onBindViewHolder(@NonNull NavigationAdapter.ViewHolder holder, int position) {
        NavigationData navigationData = this.navigationData.get(position);
        if (holder.tvNavigationName != null) {
            holder.tvNavigationName.setText(navigationData.getName());
        }
        if (holder.ivNavigation != null) {
            holder.ivNavigation.setImageResource(navigationData.getDrawableId());
            holder.ivNavigation.setTag(position);
        }

        holder.itemView.setTag(position);
        holder.itemView.setBackgroundResource(navigationData.isSelected() ? R.color.ripple_color : android.R.color.transparent);
    }

    /**
     * Getter for the {@link NavigationData} count.
     *
     * @return Size of the {@link NavigationData}.
     */
    @Override
    public int getItemCount() {
        return navigationData.size();
    }

    /**
     * Clears and fills an {@link ArrayList} with the data received.
     *
     * @param data Navigation data {@link ArrayList}.
     */
    public void refreshAdapter(ArrayList<NavigationData> data) {
        navigationData.clear();
        navigationData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * Sets the selected position in the selected position in the {@link NavigationData}.
     *
     * @param position Selected position.
     */
    public void setSelected(int position) {
        for (int i = 0; i < navigationData.size(); i++) {
            navigationData.get(i).setSelected(i == position);
        }

        notifyDataSetChanged();
    }
}
