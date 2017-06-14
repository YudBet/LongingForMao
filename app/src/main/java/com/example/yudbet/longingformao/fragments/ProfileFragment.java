package com.example.yudbet.longingformao.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yudbet.longingformao.MainActivity;
import com.example.yudbet.longingformao.R;
import com.example.yudbet.longingformao.items.Post;
import com.example.yudbet.longingformao.items.User;
import com.example.yudbet.longingformao.recyclerview.PostsGridRecyclerAdapter;
import com.example.yudbet.longingformao.recyclerview.PostsRecyclerAdapter;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/7.
 */
public class ProfileFragment extends Fragment {
    public static int STYLE_GRID = 0;
    public static int STYLE_LIST = 1;

    private ImageView usr_img;
    private TextView usr_name;
    private TextView usr_gender_school;
    private TextView posts_count, following_count, friends_count;
    private Button btn_follow;

    private BottomBar grid_list_selector;

    private RecyclerView postsRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        initUserInfo(v);
        initGridListSelector(v);
        // Inflate the layout for this fragment
        return v;
    }

    public void initUserInfo(View v) {
        usr_img = (ImageView) v.findViewById(R.id.usr_img);
        usr_name = (TextView) v.findViewById(R.id.usr_name);
        usr_gender_school = (TextView) v.findViewById(R.id.usr_gender_school);
        posts_count = (TextView) v.findViewById(R.id.posts_count);
        following_count = (TextView) v.findViewById(R.id.following_count);
        friends_count = (TextView) v.findViewById(R.id.friends_count);
        btn_follow = (Button) v.findViewById(R.id.btn_follow);

        User user = MainActivity.getSampleUser().get(5);
        String img_d = user.getImg();
        String name_d = user.getName();
        String usr_gender_school_d = user.getGender() + " · " + user.getSch_name();
        int posts_count_d = user.getPosts().size();
        int following_count_d = user.getFollowings().size();
        int friends_count_d = user.getFriends().size();

        Picasso.with(getContext()).load(img_d).resize(88, 88).centerCrop().into(usr_img);
        usr_name.setText(name_d);
        usr_gender_school.setText(usr_gender_school_d);
        posts_count.setText(posts_count_d+"");
        following_count.setText(following_count_d+"");
        friends_count.setText(friends_count_d+"");

        btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Follow!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initGridListSelector(View v) {
        grid_list_selector = (BottomBar) v.findViewById(R.id.gird_list_selector);
        postsRecyclerView = (RecyclerView) v.findViewById(R.id.posts_recycler_view);

        clearTitle(grid_list_selector);

        grid_list_selector.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_grid) initPostsRecyclerView(STYLE_GRID);
                else initPostsRecyclerView(STYLE_LIST);
            }
        });
    }

    public void initPostsRecyclerView(final int STYLE_ID) {
        ArrayList<Post> posts = MainActivity.getSamplePost();
        RecyclerView.LayoutManager lm = (STYLE_ID == STYLE_GRID) ? new GridLayoutManager(this.getActivity(), 3) : new LinearLayoutManager(this.getActivity());
        RecyclerView.Adapter adapter = (STYLE_ID == STYLE_GRID) ? new PostsGridRecyclerAdapter(getContext(), posts) : new PostsRecyclerAdapter(getContext(), posts);

        postsRecyclerView.setLayoutManager(lm);
        postsRecyclerView.setAdapter(adapter);

        postsRecyclerView.setHasFixedSize(true);
        postsRecyclerView.setFocusableInTouchMode(false);
        postsRecyclerView.setNestedScrollingEnabled(false);
    }

    public void clearTitle(BottomBar grid_list_selector) {
        ViewGroup mItemContainer = (ViewGroup) grid_list_selector.findViewById(com.roughike.bottombar.R.id.bb_bottom_bar_item_container);
        for (int i = 0; i < mItemContainer.getChildCount(); i++) {
            View viewItem = mItemContainer.getChildAt(i);
            //TITLE
            TextView titleTab = (TextView) viewItem.findViewById(com.roughike.bottombar.R.id.bb_bottom_bar_title);
            titleTab.setVisibility(View.GONE);
            //ICON
            AppCompatImageView icon = (AppCompatImageView) viewItem.findViewById(com.roughike.bottombar.R.id.bb_bottom_bar_icon);
            icon.setY(20);
            //hack for fix the color set in setActiveTabColor
            icon.setColorFilter(titleTab.getCurrentTextColor());
        }
    }
}
