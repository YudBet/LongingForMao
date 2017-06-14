package com.example.yudbet.longingformao.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ContentFrameLayout;
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
 * Created by KDD-lab on 2017/6/13.
 */

public class SinglePostFragment extends Fragment {

    RecyclerView postsRecyclerView;
    LinearLayoutManager mLayoutManager;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_single_post, container, false);
        context = v.getContext();

        initPostsRecyclerView(v);
        // Inflate the layout for this fragment
        return v;
    }

    public void initPostsRecyclerView(View v) {
        postsRecyclerView = (RecyclerView) v.findViewById(R.id.posts_recycler_view);
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(MainActivity.getSamplePost().get(0));

        mLayoutManager = new LinearLayoutManager(context);
        postsRecyclerView.setLayoutManager(mLayoutManager);

        PostsRecyclerAdapter adapter = new PostsRecyclerAdapter(context, posts);
        postsRecyclerView.setAdapter(adapter);

        postsRecyclerView.setHasFixedSize(true);
        postsRecyclerView.setFocusableInTouchMode(false);
    }
}
