package com.ljl.www.po;
/**
 * @className Remark
 * @description idea根据数据库表自动生成的Remark实体类
 * @author  22427(king0liam)
 * @date 2021/6/18 15:09
 * @version 1.0
 * @since version-0.0
 */
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Remark implements Serializable{

  private long fatherId=-9;
  private long floor=-9;
  private long toFloor=-9;
  private long clientId=-9;
  private java.sql.Timestamp remarkNewDate=new Timestamp(System.currentTimeMillis());;
  private java.sql.Timestamp remarkEditDate=new Timestamp(System.currentTimeMillis());;
  private String remarkArticle="";
  private long thumbsUpCount=0;
  private long visible=0;


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


  public long getToFloor() {
    return toFloor;
  }

  public void setToFloor(long toFloor) {
    this.toFloor = toFloor;
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


  public java.sql.Timestamp getRemarkEditDate() {
    return remarkEditDate;
  }

  public void setRemarkEditDate(java.sql.Timestamp remarkEditDate) {
    this.remarkEditDate = remarkEditDate;
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


  public long getVisible() {
    return visible;
  }

  public void setVisible(long visible) {
    this.visible = visible;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Remark remark = (Remark) o;
    return getFatherId() == remark.getFatherId() && getFloor() == remark.getFloor() && getToFloor() == remark.getToFloor() && getClientId() == remark.getClientId() && getThumbsUpCount() == remark.getThumbsUpCount() && getVisible() == remark.getVisible() && Objects.equals(getRemarkNewDate(), remark.getRemarkNewDate()) && Objects.equals(getRemarkEditDate(), remark.getRemarkEditDate()) && Objects.equals(getRemarkArticle(), remark.getRemarkArticle());
  }
}
