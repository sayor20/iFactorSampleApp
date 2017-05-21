package com.sayor.org.ifactorsampleapp.models;

/**
 * Created by Sayor on 1/28/16.
 */
public class User {

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String userID;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String userName;
    public String address;
}
