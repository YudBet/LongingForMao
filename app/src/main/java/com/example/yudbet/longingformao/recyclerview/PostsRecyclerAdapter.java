package com.example.yudbet.longingformao.recyclerview;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yudbet.longingformao.CircleTransform;
import com.example.yudbet.longingformao.R;
import com.example.yudbet.longingformao.items.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/7.
 */
public class PostsRecyclerAdapter extends RecyclerView.Adapter<PostsRecyclerViewHolder> {
    private ArrayList<Post> posts;

    Context context;
    LayoutInflater inflater;

    public PostsRecyclerAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;

        inflater = LayoutInflater.from(context);
    }


    @Override
    public PostsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_posts, parent, false);

        PostsRecyclerViewHolder viewHolder = new PostsRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PostsRecyclerViewHolder holder, int position) {
        Post post = posts.get(position);

        final String usr_img = post.getUsr_img();
        final String usr_name = post.getUsr_name();
        final String pet_info = post.getPet_info();
        final String pet_img = post.getPet_img();

        final String img = post.getImg_url();

        final int like_count = post.getLike_count();

        final ArrayList responsers = post.getResponsers();
        final ArrayList comments = post.getComments();
        final int divider_visible = (position == getItemCount() - 1) ? View.GONE : View.VISIBLE;


        Picasso.with(context).load(Uri.parse(usr_img)).resize(40, 40).into(holder.usr_img);
        Picasso.with(context).load(Uri.parse(pet_img)).resize(40, 40).transform(new CircleTransform()).into(holder.pet_img);
        Picasso.with(context).load(Uri.parse(img)).resize(1000, 1000).into(holder.img);
        holder.usr_name.setText(usr_name);
        holder.pet_info.setText(pet_info);

        holder.like_count.setText(like_count + "個讚");

        initCommentsRecyclerView(holder.comments_recycler_view, responsers, comments);
        holder.divider.setVisibility(divider_visible);

        // should decided by islike
        holder.like_unchecked.setVisibility(View.VISIBLE);
        holder.like_checked.setVisibility(View.GONE);

        holder.like_unchecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.like_unchecked.setVisibility(View.INVISIBLE);
                holder.like_checked.setVisibility(View.VISIBLE);
                holder.like_count.setText(like_count+1 + "個讚");
            }
        });
        holder.like_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.like_unchecked.setVisibility(View.VISIBLE);
                holder.like_checked.setVisibility(View.INVISIBLE);
                holder.like_count.setText(like_count + "個讚");
            }
        });
        holder.new_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment_post();
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_post();
            }
        });
    }

    public void initCommentsRecyclerView(RecyclerView comments_recycler_view, ArrayList responsers, ArrayList comments) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        comments_recycler_view.setLayoutManager(mLayoutManager);

        CommentsRecyclerAdapter adapter = new CommentsRecyclerAdapter(context, responsers, comments);
        comments_recycler_view.setAdapter(adapter);

        comments_recycler_view.setHasFixedSize(true);
        comments_recycler_view.setFocusableInTouchMode(false);
        comments_recycler_view.setNestedScrollingEnabled(false); //

        comments_recycler_view.setLayoutManager(new LinearLayoutManager(context));
    }


    public void comment_post() {
        Toast.makeText(context, "New comment", Toast.LENGTH_SHORT).show();

    }

    public void share_post() {
        Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }
}
