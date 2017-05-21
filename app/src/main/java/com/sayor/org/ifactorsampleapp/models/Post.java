package com.sayor.org.ifactorsampleapp.models;

/**
 * Created by Sayor on 1/28/16.
 */
public class Post {

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String postID;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String title;
    public String body;
}
