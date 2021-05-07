package com.ljl.www.po;

import java.sql.Timestamp;

public class ThumbsUp {

  private long postId;
  private long clientId;
  private java.sql.Timestamp thumbsUpNewDate;

  public ThumbsUp() {
  }

  public ThumbsUp(long postId, long clientId, Timestamp thumbsUpNewDate) {
    this.postId = postId;
    this.clientId = clientId;
    this.thumbsUpNewDate = thumbsUpNewDate;
  }

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

}
