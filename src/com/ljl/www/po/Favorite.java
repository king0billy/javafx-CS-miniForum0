package com.ljl.www.po;


import java.sql.Timestamp;

public class Favorite {

  private long postId;
  private long clientId;
  private java.sql.Timestamp favoriteNewDate;
  private String tag;

  public Favorite() {
  }

  public Favorite(long postId, long clientId, Timestamp favoriteNewDate, String tag) {
    this.postId = postId;
    this.clientId = clientId;
    this.favoriteNewDate = favoriteNewDate;
    this.tag = tag;
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
