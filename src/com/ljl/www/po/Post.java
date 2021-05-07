package com.ljl.www.po;


import java.sql.Timestamp;

public class Post {

  private long postId;
  private long clientId;
  private java.sql.Timestamp postNewDate;
  private String postTitle;
  private String postArticle;
  private long thumbsUpCount;
  private long favoriteCount;
  private long remarkCount;

  public Post() {
  }

  public Post(long postId, long clientId, Timestamp postNewDate, String postTitle,
              String postArticle, long thumbsUpCount, long favoriteCount, long remarkCount) {
    this.postId = postId;
    this.clientId = clientId;
    this.postNewDate = postNewDate;
    this.postTitle = postTitle;
    this.postArticle = postArticle;
    this.thumbsUpCount = thumbsUpCount;
    this.favoriteCount = favoriteCount;
    this.remarkCount = remarkCount;
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


  public java.sql.Timestamp getPostNewDate() {
    return postNewDate;
  }

  public void setPostNewDate(java.sql.Timestamp postNewDate) {
    this.postNewDate = postNewDate;
  }


  public String getPostTitle() {
    return postTitle;
  }

  public void setPostTitle(String postTitle) {
    this.postTitle = postTitle;
  }


  public String getPostArticle() {
    return postArticle;
  }

  public void setPostArticle(String postArticle) {
    this.postArticle = postArticle;
  }


  public long getThumbsUpCount() {
    return thumbsUpCount;
  }

  public void setThumbsUpCount(long thumbsUpCount) {
    this.thumbsUpCount = thumbsUpCount;
  }


  public long getFavoriteCount() {
    return favoriteCount;
  }

  public void setFavoriteCount(long favoriteCount) {
    this.favoriteCount = favoriteCount;
  }


  public long getRemarkCount() {
    return remarkCount;
  }

  public void setRemarkCount(long remarkCount) {
    this.remarkCount = remarkCount;
  }

}
