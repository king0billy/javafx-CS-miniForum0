package com.ljl.www.po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
/**
 * @className Post
 * @description idea根据数据库表自动生成的Post的实体类，自动生成equals方法以进行查询结果成功与否的比较
 * @author  22427(king0liam)
 * @date 2021/6/18 15:05
 * @version 1.0
 * @since version-0.0
 */
public class Post implements Serializable  {

  private Long postId;
  private Long clientId;
  private java.sql.Timestamp postNewDate;
  private String postTitle;
  private String postArticle;
  private Long thumbsUpCount;
  private Long favoriteCount;
  private Long remarkCount;
  private Boolean visible;

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


  public Boolean getVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Post post = (Post) o;
    return Objects.equals(getPostId(), post.getPostId()) && Objects.equals(getClientId(), post.getClientId()) && Objects.equals(getPostNewDate(), post.getPostNewDate()) && Objects.equals(getPostTitle(), post.getPostTitle()) && Objects.equals(getPostArticle(), post.getPostArticle()) && Objects.equals(getThumbsUpCount(), post.getThumbsUpCount()) && Objects.equals(getFavoriteCount(), post.getFavoriteCount()) && Objects.equals(getRemarkCount(), post.getRemarkCount()) && Objects.equals(getVisible(), post.getVisible());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPostId(), getClientId(), getPostNewDate(), getPostTitle(), getPostArticle(), getThumbsUpCount(), getFavoriteCount(), getRemarkCount(), getVisible());
  }
}
