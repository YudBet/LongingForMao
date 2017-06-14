package com.example.yudbet.longingformao.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yudbet.longingformao.CircleTransform;
import com.example.yudbet.longingformao.MainActivity;
import com.example.yudbet.longingformao.R;
import com.example.yudbet.longingformao.items.Pet;
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
public class PetFragment extends Fragment {
    public static int STYLE_GRID = 0;
    public static int STYLE_LIST = 1;

    private ImageView pet_img;
    private TextView pet_name;
    private TextView pet_gender_school;
    private TextView posts_count, following_count, friends_count;
    private Button btn_follow;

    private BottomBar grid_list_selector;

    private RecyclerView postsRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pet, container, false);

        initPetInfo(v);
        initGridListSelector(v);
        // Inflate the layout for this fragment
        return v;
    }

    public void initPetInfo(View v) {
        pet_img = (ImageView) v.findViewById(R.id.pet_img);
        pet_name = (TextView) v.findViewById(R.id.pet_name);
        pet_gender_school = (TextView) v.findViewById(R.id.pet_gender_school);
        posts_count = (TextView) v.findViewById(R.id.posts_count);
        following_count = (TextView) v.findViewById(R.id.following_count);
        friends_count = (TextView) v.findViewById(R.id.friends_count);
        btn_follow = (Button) v.findViewById(R.id.btn_follow);

        Pet pet = MainActivity.getSamplePets().get(0);
        String img_d = pet.getImg_url();
        String name_d = pet.getName();
        String pet_gender_school_d = pet.getGender() + " · " + pet.getSch_name();
        int posts_count_d = pet.getPosts().size();
        int following_count_d = pet.getFollowers().size();

        Picasso.with(getContext()).load(img_d).resize(88, 88).centerCrop().transform(new CircleTransform()).into(pet_img);
        pet_name.setText(name_d);
        pet_gender_school.setText(pet_gender_school_d);
        posts_count.setText(posts_count_d+"");
        following_count.setText(following_count_d+"");

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
