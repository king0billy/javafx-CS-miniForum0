package com.ljl.www.po;
/**
 * @className Report
 * @description idea根据数据库表自动生成的Report实体类
 * @author  22427(king0liam)
 * @date 2021/5/12 15:09
 * @version 1.0
 * @since version-0.0
 */

import java.io.Serializable;
import java.sql.Timestamp;

public class Report implements Serializable {

  private Long fatherId;
  private Long times;
  private Long clientId;
  private java.sql.Timestamp postNewDate;
  private String reportArticle;
  private String tag;

  public Report() {
  }

  public Report(Long fatherId, Long times, Long clientId, Timestamp postNewDate, String reportArticle, String tag) {
    this.fatherId = fatherId;
    this.times = times;
    this.clientId = clientId;
    this.postNewDate = postNewDate;
    this.reportArticle = reportArticle;
    this.tag = tag;
  }

  public Long getFatherId() {
    return fatherId;
  }

  public void setFatherId(Long fatherId) {
    this.fatherId = fatherId;
  }


  public Long getTimes() {
    return times;
  }

  public void setTimes(Long times) {
    this.times = times;
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
