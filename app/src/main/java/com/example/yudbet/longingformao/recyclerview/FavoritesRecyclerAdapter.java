package com.example.yudbet.longingformao.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yudbet.longingformao.CircleTransform;
import com.example.yudbet.longingformao.PetActivity;
import com.example.yudbet.longingformao.R;
import com.example.yudbet.longingformao.SinglePostActivity;
import com.example.yudbet.longingformao.items.Pet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/7.
 */
public class FavoritesRecyclerAdapter extends RecyclerView.Adapter<FavoritesRecyclerViewHolder> {
    private ArrayList<Pet> favorites;

    Context context;
    LayoutInflater inflater;

    public FavoritesRecyclerAdapter(Context context, ArrayList<Pet> favorites) {
        this.context = context;
        this.favorites = favorites;

        inflater = LayoutInflater.from(context);
    }


    @Override
    public FavoritesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_favorites, parent, false);

        FavoritesRecyclerViewHolder viewHolder = new FavoritesRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavoritesRecyclerViewHolder holder, int position) {
        Pet pet = favorites.get(position);

        final String img_url = pet.getImg_url();
        final String pet_info = pet.getPetInfo();
        final String phy_info = pet.getPhysiologicalInfo();
        final String follower_count = pet.getFollowers().size() + "";
        final String posts_count = pet.getPosts().size() + "";
        final int divider_visible = (position == getItemCount() - 1) ? View.GONE : View.VISIBLE;

        Picasso.with(context).load(Uri.parse(img_url)).resize(50, 50).transform(new CircleTransform()).into(holder.img);
        holder.pet_info.setText(pet_info);
        holder.phy_info.setText(phy_info);
        holder.follower_count.setText(follower_count);
        holder.posts_count.setText(posts_count);
        holder.divider.setVisibility(divider_visible);

        holder.to_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PetActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }
}
