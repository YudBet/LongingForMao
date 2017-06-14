package com.example.yudbet.longingformao.items;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Mist on 2017/6/6.
 */
public class Pet {
    private int id;
    private int age;
    private int gender; // 0 for female, 1 for male
    private boolean is_ligation;
    private LatLng position;
    private String name;
    private String sch_name;
    private String color;
    private String img_url;
    private ArrayList<Integer> posts;
    private ArrayList<Integer> followers;

    public Pet(int id, int age, int gender,
               boolean is_ligation,
               LatLng position,
               String name, String sch_name, String color, String img_url,
               ArrayList<Integer> posts, ArrayList<Integer> followers) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.position = position;
        this.is_ligation = is_ligation;
        this.name = name;
        this.sch_name = sch_name;
        this.color = color;
        this.img_url = img_url;
        this.posts = posts;
        this.followers = followers;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPetInfo() {
        return name + " - " + this.sch_name;
    }

    public String getPhysiologicalInfo() {
        String gen_str = (gender == 1) ? "公" : "母";
        return gen_str + " · " + color + " · " + age + "歲";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        if (gender == 0)
            return "母";
        return "公";
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean is_ligation() {
        return is_ligation;
    }

    public void setIs_ligation(boolean is_ligation) {
        this.is_ligation = is_ligation;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public ArrayList<Integer> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Integer> posts) {
        this.posts = posts;
    }

    public ArrayList<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<Integer> followers) {
        this.followers = followers;
    }
}
