package com.ljl.www.po;


import java.sql.Timestamp;

public class Report {

  private long fatherId;
  private long times;
  private long clientId;
  private java.sql.Timestamp postNewDate;
  private String reportArticle;
  private String tag;

  public Report() {
  }

  public Report(long fatherId, long times, long clientId, Timestamp postNewDate, String reportArticle, String tag) {
    this.fatherId = fatherId;
    this.times = times;
    this.clientId = clientId;
    this.postNewDate = postNewDate;
    this.reportArticle = reportArticle;
    this.tag = tag;
  }

  public long getFatherId() {
    return fatherId;
  }

  public void setFatherId(long fatherId) {
    this.fatherId = fatherId;
  }


  public long getTimes() {
    return times;
  }

  public void setTimes(long times) {
    this.times = times;
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


  public String getReportArticle() {
    return reportArticle;
  }

  public void setReportArticle(String reportArticle) {
    this.reportArticle = reportArticle;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

}
