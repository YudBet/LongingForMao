package com.example.yudbet.longingformao.items;


/**
 * Created by Mist on 2017/6/7.
 */
public class Notification {
    private int pet_id;
    private String pet_name;
    private String sch_name;
    private String img_url;
    private int post_count;
    private int feed_hour;

    public Notification(int pet_id, String pet_name, String sch_name, String img_url, int post_count, int feed_hour) {
        this.pet_id = pet_id;
        this.pet_name = pet_name;
        this.sch_name = sch_name;
        this.img_url = img_url;
        this.post_count = post_count;
        this.feed_hour = feed_hour;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public String getPetInfo() {
        return pet_name + " - " + this.sch_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public void setSch_name(String sch_name) {
        this.sch_name = sch_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPostInfo() {
        return post_count + " 則新貼文";
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }

    public String getFeedInfo() {
        return feed_hour + " 小時前更新餵食記錄";
    }

    public void setFeed_hour(int feed_hour) {
        this.feed_hour = feed_hour;
    }
}
