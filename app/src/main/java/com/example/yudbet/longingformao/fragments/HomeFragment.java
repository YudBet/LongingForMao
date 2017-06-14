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
import com.example.yudbet.longingformao.items.Post;
import com.example.yudbet.longingformao.recyclerview.PostsRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/7.
 */
public class HomeFragment extends Fragment {

    RecyclerView postsRecyclerView;
    LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        initPostsRecyclerView(v);
        // Inflate the layout for this fragment
        return v;
    }

    public void initPostsRecyclerView(View v) {
        postsRecyclerView = (RecyclerView) v.findViewById(R.id.posts_recycler_view);
        ArrayList<Post> posts = MainActivity.getSamplePost();

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        postsRecyclerView.setLayoutManager(mLayoutManager);

        PostsRecyclerAdapter adapter = new PostsRecyclerAdapter(getContext(), posts);
        postsRecyclerView.setAdapter(adapter);

        postsRecyclerView.setHasFixedSize(true);
        postsRecyclerView.setFocusableInTouchMode(false);

        postsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
