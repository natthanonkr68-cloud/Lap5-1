package com.example.lap51;

import java.io.Serializable;

public abstract class Note implements Serializable {
    private String title;
    private User user;

    public Note(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public abstract String getNoteType();
    public abstract String getDetails();
}
