package com.brylkowski.mahumito.mahumito.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brylkowski.mahumito.mahumito.R;
import com.brylkowski.mahumito.mahumito.activities.BeaconDetailsActivity;
import com.brylkowski.mahumito.mahumito.models.Beacon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BeaconsViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private final List<Beacon> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;
    private Fragment fragment;



    public BeaconsViewAdapter(
        List<Beacon> items,
        OnListFragmentInteractionListener listener,
        Fragment fragment
    ) {
        mValues = items;
        mListener = listener;
        this.fragment = fragment;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
            View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
            //inflate your layout and pass it to view holder
            return new VHItem(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final VHItem itemHolder = (VHItem) holder;
        final Beacon beacon = mValues.get(position);
        itemHolder.mItem = mValues.get(position);
        itemHolder.titleView.setText(mValues.get(position).name());
        itemHolder.descView.setText(mValues.get(position).description());
        String backgroundUrl = mValues.get(position).missionImage();
        if(backgroundUrl.trim().length() != 0)
            Picasso.with(context).load(backgroundUrl).into(itemHolder.imageView);
        ((VHItem) holder).mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BeaconDetailsActivity.class);
                intent.putExtra("BEACON", beacon);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, (ImageView)((VHItem) holder).mView.findViewById(R.id.image), "image");
                context.startActivity(intent, options.toBundle());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView titleView;
        private final TextView descView;
        private final ImageView imageView;
        public Beacon mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleView = (TextView) view.findViewById(R.id.title);
            descView = (TextView) view.findViewById(R.id.description);
            imageView = (ImageView) view.findViewById(R.id.image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titleView.getText() + "'";
        }
    }

    class VHItem extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView titleView;
        private final TextView descView;
        private final ImageView imageView;
        public Beacon mItem;

        public VHItem(View view) {
            super(view);
            mView = view;
            titleView = (TextView) view.findViewById(R.id.title);
            descView = (TextView) view.findViewById(R.id.description);
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }

}
