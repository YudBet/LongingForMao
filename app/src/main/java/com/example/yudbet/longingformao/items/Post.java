package com.example.yudbet.longingformao.items;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/6.
 */
public class Post {
    private int id;
    private int pet_id;
    private int usr_id;
    private boolean is_feed;
    private String usr_name, usr_img;
    private String pet_info, pet_img;
    private String img_url;
    private ArrayList<String> responsers;
    private ArrayList<String> comments;
    private int like_count;

    public Post(int id, int pet_id, int usr_id, boolean is_feed, String usr_name, String usr_img, String pet_info, String pet_img, String img_url, ArrayList<String> responsers, ArrayList<String> comments, int like_count) {
        this.id = id;
        this.pet_id = pet_id;
        this.usr_id = usr_id;
        this.is_feed = is_feed;
        this.usr_name = usr_name;
        this.usr_img = usr_img;
        this.pet_info = pet_info;
        this.pet_img = pet_img;
        this.img_url = img_url;
        this.responsers = responsers;
        this.comments = comments;
        this.like_count = like_count;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public boolean is_feed() {
        return is_feed;
    }

    public void setIs_feed(boolean is_feed) {
        this.is_feed = is_feed;
    }

    public String getUsr_name() {
        return usr_name;
    }

    public void setUsr_name(String usr_name) {
        this.usr_name = usr_name;
    }

    public String getUsr_img() {
        return usr_img;
    }

    public void setUsr_img(String usr_img) {
        this.usr_img = usr_img;
    }

    public String getPet_info() {
        return pet_info;
    }

    public void setPet_info(String pet_info) {
        this.pet_info = pet_info;
    }

    public String getPet_img() {
        return pet_img;
    }

    public void setPet_img(String pet_img) {
        this.pet_img = pet_img;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public ArrayList<String> getResponsers() {
        return responsers;
    }

    public void setResponsers(ArrayList<String> responsers) {
        this.responsers = responsers;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }
}
