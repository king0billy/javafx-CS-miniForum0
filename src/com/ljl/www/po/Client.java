package com.ljl.www.po;


import java.sql.Timestamp;

public class Client {

  private Long clientId;
  private String clientTel;
  private String clientPassword;
  private String clientNickname;
  private String clientSex;
  private String clientAddress;
  private String clientDescription;
  private java.sql.Timestamp clientEnrollDate;
  private long clientPrivilege;

  public Client() {
  }

  public Client(long clientId, String clientTel, String clientPassword,
                String clientNickname, String clientSex, String clientAddress,
                String clientDescription, Timestamp clientEnrollDate, long clientPrivilege) {
    this.clientId = clientId;
    this.clientTel = clientTel;
    this.clientPassword = clientPassword;
    this.clientNickname = clientNickname;
    this.clientSex = clientSex;
    this.clientAddress = clientAddress;
    this.clientDescription = clientDescription;
    this.clientEnrollDate = clientEnrollDate;
    this.clientPrivilege = clientPrivilege;
  }

  public long getClientId() {
    return clientId;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }


  public String getClientTel() {
    return clientTel;
  }

  public void setClientTel(String clientTel) {
    this.clientTel = clientTel;
  }


  public String getClientPassword() {
    return clientPassword;
  }

  public void setClientPassword(String clientPassword) {
    this.clientPassword = clientPassword;
  }


  public String getClientNickname() {
    return clientNickname;
  }

  public void setClientNickname(String clientNickname) {
    this.clientNickname = clientNickname;
  }


  public String getClientSex() {
    return clientSex;
  }

  public void setClientSex(String clientSex) {
    this.clientSex = clientSex;
  }


  public String getClientAddress() {
    return clientAddress;
  }

  public void setClientAddress(String clientAddress) {
    this.clientAddress = clientAddress;
  }


  public String getClientDescript() {
    return clientDescription;
  }

  public void setClientDescript(String clientDescript) {
    this.clientDescription = clientDescript;
  }


  public java.sql.Timestamp getClientEnrollDate() {
    return clientEnrollDate;
  }

  public void setClientEnrollDate(java.sql.Timestamp clientEnrollDate) {
    this.clientEnrollDate = clientEnrollDate;
  }


  public long getClientPrivilege() {
    return clientPrivilege;
  }

  public void setClientPrivilege(long clientPrivilege) {
    this.clientPrivilege = clientPrivilege;
  }

}
