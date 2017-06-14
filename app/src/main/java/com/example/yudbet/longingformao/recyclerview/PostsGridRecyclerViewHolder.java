package com.example.yudbet.longingformao.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yudbet.longingformao.MainActivity;
import com.example.yudbet.longingformao.PetActivity;
import com.example.yudbet.longingformao.R;
import com.example.yudbet.longingformao.SinglePostActivity;
import com.example.yudbet.longingformao.items.Pet;

/**
 * Created by Mist on 2017/6/7.
 */
public class PostsGridRecyclerViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    Context context;

    public PostsGridRecyclerViewHolder(final View itemView) {
        super(itemView);
        context = itemView.getContext();

        img = (ImageView) itemView.findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
