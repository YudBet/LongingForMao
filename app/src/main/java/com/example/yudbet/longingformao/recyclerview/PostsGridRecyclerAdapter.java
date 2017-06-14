package com.example.yudbet.longingformao.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yudbet.longingformao.CircleTransform;
import com.example.yudbet.longingformao.MainActivity;
import com.example.yudbet.longingformao.PetActivity;
import com.example.yudbet.longingformao.R;
import com.example.yudbet.longingformao.SinglePostActivity;
import com.example.yudbet.longingformao.items.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/7.
 */
public class PostsGridRecyclerAdapter extends RecyclerView.Adapter<PostsGridRecyclerViewHolder> {
    private ArrayList<Post> posts;

    Context context;
    LayoutInflater inflater;

    public PostsGridRecyclerAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;

        inflater = LayoutInflater.from(context);
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    @Override
    public PostsGridRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_posts_grid, parent, false);

        PostsGridRecyclerViewHolder viewHolder = new PostsGridRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PostsGridRecyclerViewHolder holder, final int position) {
        Post post = posts.get(position);

        final String img = post.getImg_url();
        Picasso.with(context).load(Uri.parse(img)).resize(1000, 1000).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SinglePostActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
