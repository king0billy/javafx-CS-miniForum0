package com.ljl.www.po;


import java.sql.Timestamp;

public class Remark {

  private long fatherId;
  private long floor;
  private long clientId;
  private java.sql.Timestamp remarkNewDate;
  private String remarkArticle;
  private long thumbsUpCount;

  public Remark() {
  }

  public Remark(long fatherId, long floor, long clientId, Timestamp remarkNewDate,
                String remarkArticle, long thumbsUpCount) {
    this.fatherId = fatherId;
    this.floor = floor;
    this.clientId = clientId;
    this.remarkNewDate = remarkNewDate;
    this.remarkArticle = remarkArticle;
    this.thumbsUpCount = thumbsUpCount;
  }

  public long getFatherId() {
    return fatherId;
  }

  public void setFatherId(long fatherId) {
    this.fatherId = fatherId;
  }


  public long getFloor() {
    return floor;
  }

  public void setFloor(long floor) {
    this.floor = floor;
  }


  public long getClientId() {
    return clientId;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }


  public java.sql.Timestamp getRemarkNewDate() {
    return remarkNewDate;
  }

  public void setRemarkNewDate(java.sql.Timestamp remarkNewDate) {
    this.remarkNewDate = remarkNewDate;
  }


  public String getRemarkArticle() {
    return remarkArticle;
  }

  public void setRemarkArticle(String remarkArticle) {
    this.remarkArticle = remarkArticle;
  }


  public long getThumbsUpCount() {
    return thumbsUpCount;
  }

  public void setThumbsUpCount(long thumbsUpCount) {
    this.thumbsUpCount = thumbsUpCount;
  }

}
