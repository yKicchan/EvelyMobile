package com.evely.android.evelymobileapplication.model;

/**
 * Project EvelyMobileApplication
 * Working on Comment
 * Created by Shion T. Fujie on 2017/12/06.
 */
public class Comment {
    private String textContent;
    private User commenter;

    public Comment(String textContent, User commenter){
        this.textContent = textContent;
        this.commenter = commenter;
    }

    public String getTextContent() {
        return textContent;
    }

    public User getCommenter() {
        return commenter;
    }
}
