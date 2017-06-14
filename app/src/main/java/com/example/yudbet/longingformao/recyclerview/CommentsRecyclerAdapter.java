package com.example.yudbet.longingformao.recyclerview;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yudbet.longingformao.CircleTransform;
import com.example.yudbet.longingformao.R;
import com.example.yudbet.longingformao.items.Pet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/7.
 */
public class CommentsRecyclerAdapter extends RecyclerView.Adapter<CommentsRecyclerViewHolder> {
    private ArrayList<String> responsers, comments;

    Context context;
    LayoutInflater inflater;

    public CommentsRecyclerAdapter(Context context, ArrayList responsers, ArrayList comments) {
        this.context = context;
        this.responsers = responsers;
        this.comments = comments;

        inflater = LayoutInflater.from(context);
    }


    @Override
    public CommentsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_comments, parent, false);

        CommentsRecyclerViewHolder viewHolder = new CommentsRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommentsRecyclerViewHolder holder, int position) {
        final String responser = responsers.get(position);
        final String comment = comments.get(position);

        holder.responser.setText(responser);
        holder.comment.setText(comment);
    }

    @Override
    public int getItemCount() {
        return responsers.size();
    }
}
