package com.example.glmgracy.reminders;

/**
 * Created by 桂新 on 2016/4/21.
 */
public class Reminder {
    private int mId;
    private String mContent;
    private int mImportant;

    public Reminder(String content, int id, int important) {
        mContent = content;
        mId = id;
        mImportant = important;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getImportant() {
        return mImportant;
    }

    public void setImportant(int important) {
        mImportant = important;
    }
}
