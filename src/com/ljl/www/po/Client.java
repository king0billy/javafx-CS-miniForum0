package com.ljl.www.po;


import java.io.Serializable;
import java.sql.Timestamp;

public class Client extends Object implements Serializable {

  private Long clientId;
  private String clientTel;
  private String clientPassword;
  private String clientNickname;
  private String clientSex;
  private String clientAddress;
  private String clientDescription;
  private java.sql.Timestamp clientEnrollDate;
  private Long clientPrivilege;

  public Client() {
  }

  public Client(Long clientId, String clientTel, String clientPassword,
                String clientNickname, String clientSex, String clientAddress,
                String clientDescription, Timestamp clientEnrollDate, Long clientPrivilege) {
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

  public Client(Long clientId, String clientTel, String clientPassword) {
    this.clientId = clientId;
    this.clientTel = clientTel;
    this.clientPassword = clientPassword;
  }
  //@Override
  public boolean equals(Client client){
    if(client==null)return false;
    else {
      if (this.clientId != client.getClientId() || this.clientTel != client.getClientTel() |
              this.clientNickname!=client.getClientNickname()|| this.clientPrivilege!=client.getClientPrivilege()
      ) {
        return false;
      }
    }
    return true;
  }
  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
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


  public Long getClientPrivilege() {
    return clientPrivilege;
  }

  public void setClientPrivilege(Long clientPrivilege) {
    this.clientPrivilege = clientPrivilege;
  }

}
