package com.ljl.www.po;

import java.io.Serializable;
import java.util.Objects;

/**
 * @className ThumbsUp
 * @description idea根据数据库表自动生成的ThumbsUp实体类
 * @author  22427(king0liam)
 * @date 2021/5/12 15:09
 * @version 1.0
 * @since version-0.0
 */

public class ThumbsUp implements Serializable {

  private long postId;
  private long clientId;
  private java.sql.Timestamp thumbsUpNewDate;
  private java.sql.Timestamp thumbsUpDropDate;
  private boolean visible;


  public long getPostId() {
    return postId;
  }

  public void setPostId(long postId) {
    this.postId = postId;
  }


  public long getClientId() {
    return clientId;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }


  public java.sql.Timestamp getThumbsUpNewDate() {
    return thumbsUpNewDate;
  }

  public void setThumbsUpNewDate(java.sql.Timestamp thumbsUpNewDate) {
    this.thumbsUpNewDate = thumbsUpNewDate;
  }


  public java.sql.Timestamp getThumbsUpDropDate() {
    return thumbsUpDropDate;
  }

  public void setThumbsUpDropDate(java.sql.Timestamp thumbsUpDropDate) {
    this.thumbsUpDropDate = thumbsUpDropDate;
  }


  public boolean getVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ThumbsUp thumbsUp = (ThumbsUp) o;
    return getPostId() == thumbsUp.getPostId() && getClientId() == thumbsUp.getClientId() && getVisible() == thumbsUp.getVisible() && Objects.equals(getThumbsUpNewDate(), thumbsUp.getThumbsUpNewDate()) && Objects.equals(getThumbsUpDropDate(), thumbsUp.getThumbsUpDropDate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPostId(), getClientId(), getThumbsUpNewDate(), getThumbsUpDropDate(), getVisible());
  }
}
