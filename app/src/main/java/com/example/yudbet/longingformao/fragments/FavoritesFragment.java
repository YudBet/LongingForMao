package com.example.yudbet.longingformao.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yudbet.longingformao.MainActivity;
import com.example.yudbet.longingformao.R;
import com.example.yudbet.longingformao.items.Pet;
import com.example.yudbet.longingformao.recyclerview.FavoritesRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/7.
 */
public class FavoritesFragment extends Fragment {

    RecyclerView favoritesRecyclerView;
    LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);

        initNotificationRecyclerView(v);
        // Inflate the layout for this fragment
        return v;
    }

    public void initNotificationRecyclerView(View v) {
        favoritesRecyclerView = (RecyclerView) v.findViewById(R.id.favorites_recycler_view);
        ArrayList<Pet> pets = MainActivity.getSamplePets();

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        favoritesRecyclerView.setLayoutManager(mLayoutManager);

        FavoritesRecyclerAdapter adapter = new FavoritesRecyclerAdapter(getContext(), pets);
        favoritesRecyclerView.setAdapter(adapter);

        favoritesRecyclerView.setHasFixedSize(true);
        favoritesRecyclerView.setFocusableInTouchMode(false);

        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
