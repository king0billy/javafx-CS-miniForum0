package com.ljl.www.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class ThumbsUp implements Serializable {

  private Long postId;
  private Long clientId;
  private java.sql.Timestamp thumbsUpNewDate;

  public ThumbsUp() {
  }

  public ThumbsUp(Long postId, Long clientId, Timestamp thumbsUpNewDate) {
    this.postId = postId;
    this.clientId = clientId;
    this.thumbsUpNewDate = thumbsUpNewDate;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }


  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }


  public java.sql.Timestamp getThumbsUpNewDate() {
    return thumbsUpNewDate;
  }

  public void setThumbsUpNewDate(java.sql.Timestamp thumbsUpNewDate) {
    this.thumbsUpNewDate = thumbsUpNewDate;
  }

}
