package com.gusrinda.kodetree.Model;

public class User {

    private String id;
    private String username;
    private int point = 0;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }


    public User setPoint(int point){
        this.point = point;
        return this;
    }

    public User(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoint() {
        return point;
    }
}
