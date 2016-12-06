package com.fjrcloud.lin.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by lin on 2016/12/6.
 */
public class InspectMulti implements MultiItemEntity, Serializable {

    public static final int BIG_IMG = 0;
    public static final int CLICK_IMG = 1;
    public static final int COMMENT = 2;
    public static final int MORE = 3;

    private int itemType;

    private CommentImg commentImg;
    private CommentMore commentMore;
    private CommentBean commentBean;

    public InspectMulti(int itemType, CommentImg commentImg) {
        this.itemType = itemType;
        this.commentImg = commentImg;
    }

    public InspectMulti(int itemType, CommentMore commentMore) {
        this.itemType = itemType;
        this.commentMore = commentMore;
    }

    public InspectMulti(int itemType, CommentBean commentBean) {
        this.itemType = itemType;
        this.commentBean = commentBean;
    }

    public CommentImg getCommentImg() {
        return commentImg;
    }

    public void setCommentImg(CommentImg commentImg) {
        this.commentImg = commentImg;
    }

    public CommentMore getCommentMore() {
        return commentMore;
    }

    public void setCommentMore(CommentMore commentMore) {
        this.commentMore = commentMore;
    }

    public CommentBean getCommentBean() {
        return commentBean;
    }

    public void setCommentBean(CommentBean commentBean) {
        this.commentBean = commentBean;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
