package com.social.media.socialmedia.model;


import javax.persistence.*;
import java.util.Date;
@Entity
public class UserPageLike {
    @Id
    int userId;
    int pageId;
    Date createDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
