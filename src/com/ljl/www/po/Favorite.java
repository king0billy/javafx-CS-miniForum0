package com.ljl.www.po;


import java.io.Serializable;
import java.sql.Timestamp;

public class Favorite implements Serializable {

  private Long postId;
  private Long clientId;
  private java.sql.Timestamp favoriteNewDate;
  private String tag;

  public Favorite() {
  }

  public Favorite(Long postId, Long clientId, Timestamp favoriteNewDate, String tag) {
    this.postId = postId;
    this.clientId = clientId;
    this.favoriteNewDate = favoriteNewDate;
    this.tag = tag;
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


  public java.sql.Timestamp getFavoriteNewDate() {
    return favoriteNewDate;
  }

  public void setFavoriteNewDate(java.sql.Timestamp favoriteNewDate) {
    this.favoriteNewDate = favoriteNewDate;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

}
