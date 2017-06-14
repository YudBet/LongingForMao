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
import com.example.yudbet.longingformao.items.Notification;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/7.
 */
public class NotificationRecyclerAdapter extends RecyclerView.Adapter<NotificationRecyclerViewHolder> {
    private ArrayList<Notification> notifications;

    Context context;
    LayoutInflater inflater;

    public NotificationRecyclerAdapter(Context context, ArrayList<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;

        inflater = LayoutInflater.from(context);
    }


    @Override
    public NotificationRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_notification, parent, false);

        NotificationRecyclerViewHolder viewHolder = new NotificationRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NotificationRecyclerViewHolder holder, int position) {
        Notification notification = notifications.get(position);

        final String img_url = notification.getImg_url();
        final String pet_info = notification.getPetInfo();
        final String post_info = notification.getPostInfo();
        final String feed_info = notification.getFeedInfo();
        final int divider_visible = (position == getItemCount() - 1) ? View.GONE : View.VISIBLE;

        Picasso.with(context).load(Uri.parse(img_url)).resize(50, 50).transform(new CircleTransform()).into(holder.img);
        holder.pet_info.setText(pet_info);
        holder.new_post_info.setText(post_info);
        holder.new_feed_info.setText(feed_info);
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
        return notifications.size();
    }
}
