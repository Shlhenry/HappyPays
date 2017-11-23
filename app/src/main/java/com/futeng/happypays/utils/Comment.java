package com.futeng.happypays.utils;

/**
 * Created by android on 2017/10/14.
 */

public class Comment {
    private String userName;
    private String contentText;
    private String indexComment;
    private String loveCount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getIndexComment() {
        return indexComment;
    }

    public void setIndexComment(String indexComment) {
        this.indexComment = indexComment;
    }

    public String getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(String loveCount) {
        this.loveCount = loveCount;
    }
}
