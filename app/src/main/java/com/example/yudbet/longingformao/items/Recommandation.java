package com.example.yudbet.longingformao.items;

/**
 * Created by admin on 2017/6/12.
 */

public class Recommandation {
    private int idxOfAnimal;
    private int img;
    private String type;
    private String gender;
    private String location;
    private String age;

    public Recommandation(int idxOfAnimal, int img, String type, String gender, String location, String age) {
        this.idxOfAnimal = idxOfAnimal;
        this.img = img;
        this.type = type;
        this.gender = gender;
        this.location = location;
        this.age = age;
    }

    public int getIdxofanimal() {
        return idxOfAnimal;
    }

    public void setIdxofanimal(int idxofanimal) {
        this.idxOfAnimal = idxofanimal;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        if (gender.equals("M"))
            return "公";
        return "母";
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() { return this.location; }

    public void setLocation(String location) { this.location = location; }

    public String getAge() {
        if (age.equals("CHILD"))
            return "幼年";
        return "成年";
    }

    public void setAge(String age) {
        this.age = age;
    }
}
