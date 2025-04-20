package com.example.second_retro_java;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("id")
    private int id;

    @SerializedName("ename")
    private String name;

    @SerializedName("eemail")
    private String email;

    @SerializedName("emob")
    private String mobile;

    public Users(int id, String name, String email, String mobile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }
}
