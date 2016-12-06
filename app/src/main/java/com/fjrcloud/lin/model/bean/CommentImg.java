package com.fjrcloud.lin.model.bean;

/**
 * Created by lin on 2016/12/6.
 */
public class CommentImg {

    private int imgSrc;
    // 0:大图  1:点击跳转图片
    private int type;
    // 0: 表扬  // 1:批评
    private int comment;

    public CommentImg(int imgSrc, int type, int comment) {
        this.imgSrc = imgSrc;
        this.type = type;
        this.comment = comment;
    }

    public CommentImg(int type, int imgSrc) {
        this.type = type;
        this.imgSrc = imgSrc;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
