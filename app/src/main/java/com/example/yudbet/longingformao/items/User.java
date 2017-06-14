package com.example.yudbet.longingformao.items;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/6.
 */
public class User {
    private int id;
    private int gender; // 0 for female, 1 for male
    private String img;
    private String name;
    private String sch_name;
    private ArrayList<Integer> posts;
    private ArrayList<Integer> followings;
    private ArrayList<Integer> friends;
    private ArrayList<Integer> pets;

    public User(int id, int gender,
                String img, String name, String sch_name,
                ArrayList<Integer> posts, ArrayList<Integer> followings, ArrayList<Integer> friends, ArrayList<Integer> pets) {
        this.id = id;
        this.gender = gender;
        this.img = img;
        this.name = name;
        this.sch_name = sch_name;
        this.posts = posts;
        this.followings = followings;
        this.friends = friends;
        this.pets = pets;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        if (gender == 0)
            return "女";
        return "男";
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSch_name() {
        return sch_name;
    }

    public void setSch_name(String sch_name) {
        this.sch_name = sch_name;
    }

    public ArrayList<Integer> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Integer> posts) {
        this.posts = posts;
    }

    public ArrayList<Integer> getFollowings() {
        return followings;
    }

    public void setFollowings(ArrayList<Integer> followings) {
        this.followings = followings;
    }

    public ArrayList<Integer> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Integer> friends) {
        this.friends = friends;
    }

    public ArrayList<Integer> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Integer> pets) {
        this.pets = pets;
    }
}
