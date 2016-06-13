package com.brylkowski.mahumito.mahumito.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.brylkowski.mahumito.mahumito.R;
import com.brylkowski.mahumito.mahumito.commands.GetBeacons;
import com.brylkowski.mahumito.mahumito.commands.GetMyBeacons;
import com.brylkowski.mahumito.mahumito.commands.utilities.AsyncCommandWrapper;
import com.brylkowski.mahumito.mahumito.commands.utilities.CommandCallbackInterface;
import com.brylkowski.mahumito.mahumito.fragments.dummy.DummyContent;
import com.brylkowski.mahumito.mahumito.fragments.dummy.DummyContent.DummyItem;
import com.brylkowski.mahumito.mahumito.models.Beacon;
import com.brylkowski.mahumito.mahumito.models.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NearbyFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private int initialized = 0;
    List<Beacon> items;
    private NearbyRecyclerViewAdapter adapter;
    private View view;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NearbyFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NearbyFragment newInstance(int columnCount) {
        NearbyFragment fragment = new NearbyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState
    ) {
        try{
            view = inflater.inflate(R.layout.fragment_nearby_list, container, false);
        } catch (InflateException e){
            //asdasdasd
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        // Set the adapter
        if (recyclerView instanceof RecyclerView) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            items = new ArrayList<>();
            adapter = new NearbyRecyclerViewAdapter(items, null, this);
            AsyncCommandWrapper wrapper = new AsyncCommandWrapper(new CommandCallbackInterface<List<Beacon>>() {
                @Override
                public void success(List<Beacon> objectResponse) {
                    items.addAll(objectResponse);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void failure(Throwable reason) {
                    reason.printStackTrace();
                }
            });
            wrapper.doInBackground(new GetBeacons());

            recyclerView.setAdapter(adapter);
        }
        final NestedScrollView scrollView = (NestedScrollView) view.findViewById(R.id.scroll_view);
        recyclerView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {
                initialized++;
                if(initialized < 13){
                    scrollView.setScrollY(0); //for horizontalScrollView
                }

            }
        });
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                                           + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
