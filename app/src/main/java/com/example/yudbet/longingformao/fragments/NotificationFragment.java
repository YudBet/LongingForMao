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
import com.example.yudbet.longingformao.items.Notification;
import com.example.yudbet.longingformao.recyclerview.NotificationRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/7.
 */
public class NotificationFragment extends Fragment {

    RecyclerView notificationRecyclerView;
    LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        initNotificationRecyclerView(v);

        // Inflate the layout for this fragment
        return v;
    }

    public void initNotificationRecyclerView(View v) {
        notificationRecyclerView = (RecyclerView) v.findViewById(R.id.notification_recycler_view);
        ArrayList<Notification> notifications = MainActivity.getSampleNotifications();

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        notificationRecyclerView.setLayoutManager(mLayoutManager);

        NotificationRecyclerAdapter adapter = new NotificationRecyclerAdapter(getContext(), notifications);
        notificationRecyclerView.setAdapter(adapter);

        notificationRecyclerView.setHasFixedSize(true);
        notificationRecyclerView.setFocusableInTouchMode(false);

        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
