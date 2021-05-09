package com.ljl.www.po;


import java.io.Serializable;
import java.sql.Timestamp;

public class Remark implements Serializable {

  private Long fatherId;
  private Long floor;
  private Long clientId;
  private java.sql.Timestamp remarkNewDate;
  private String remarkArticle;
  private Long thumbsUpCount;

  public Remark() {
  }

  public Remark(Long fatherId, Long floor, Long clientId, Timestamp remarkNewDate,
                String remarkArticle, Long thumbsUpCount) {
    this.fatherId = fatherId;
    this.floor = floor;
    this.clientId = clientId;
    this.remarkNewDate = remarkNewDate;
    this.remarkArticle = remarkArticle;
    this.thumbsUpCount = thumbsUpCount;
  }

  public Long getFatherId() {
    return fatherId;
  }

  public void setFatherId(Long fatherId) {
    this.fatherId = fatherId;
  }


  public Long getFloor() {
    return floor;
  }

  public void setFloor(Long floor) {
    this.floor = floor;
  }


  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
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


  public Long getThumbsUpCount() {
    return thumbsUpCount;
  }

  public void setThumbsUpCount(Long thumbsUpCount) {
    this.thumbsUpCount = thumbsUpCount;
  }

}
