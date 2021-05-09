package com.ljl.www.po;


import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable {

  private Long postId;
  private Long clientId;
  private java.sql.Timestamp postNewDate;
  private String postTitle;
  private String postArticle;
  private Long thumbsUpCount;
  private Long favoriteCount;
  private Long remarkCount;

  public Post() {
  }

  public Post(Long postId, Long clientId, Timestamp postNewDate, String postTitle,
              String postArticle, Long thumbsUpCount, Long favoriteCount, Long remarkCount) {
    this.postId = postId;
    this.clientId = clientId;
    this.postNewDate = postNewDate;
    this.postTitle = postTitle;
    this.postArticle = postArticle;
    this.thumbsUpCount = thumbsUpCount;
    this.favoriteCount = favoriteCount;
    this.remarkCount = remarkCount;
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


  public Long getThumbsUpCount() {
    return thumbsUpCount;
  }

  public void setThumbsUpCount(Long thumbsUpCount) {
    this.thumbsUpCount = thumbsUpCount;
  }


  public Long getFavoriteCount() {
    return favoriteCount;
  }

  public void setFavoriteCount(Long favoriteCount) {
    this.favoriteCount = favoriteCount;
  }


  public Long getRemarkCount() {
    return remarkCount;
  }

  public void setRemarkCount(Long remarkCount) {
    this.remarkCount = remarkCount;
  }

}
