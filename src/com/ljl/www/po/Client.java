package com.ljl.www.po;


import java.io.Serializable;
import java.sql.Timestamp;

public class Client extends Object implements Serializable {
//-9223372036854775808L
  private Long clientId=-99L;
  private String clientTel=new String("-99");
  private String clientPassword=new String("-99");
  private String clientNickname=new String("-99");
  private String clientSex=new String("-99");
  private String clientAddress=new String("shit");
  private String clientDescription=new String("shit");
  private java.sql.Timestamp clientEnrollDate=new Timestamp(System.currentTimeMillis());
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
    this.clientNickname=new String("-99");
    clientSex=new String("-99");
    clientAddress=new String("shit");
    clientDescription=new String("shit");
    clientEnrollDate=new Timestamp(System.currentTimeMillis());
    clientPrivilege=0L;//侧面说明构造方法后于属性赋值
  }
  public Client(String clientTel, String clientPassword) {
    this.clientId = -99L;
    this.clientTel = clientTel;
    this.clientPassword = clientPassword;
    this.clientNickname=new String("-99");
    clientSex=new String("-99");
    clientAddress=new String("shit");
    clientDescription=new String("shit");
    clientEnrollDate=new Timestamp(System.currentTimeMillis());
    clientPrivilege=0L;//侧面说明构造方法后于属性赋值
  }
  //@Override
  public boolean equals(Client client){
    if(client==null)return false;
    else {
      if (
              this.clientId.equals( client.getClientId())==false||this.clientEnrollDate.equals(client.getClientEnrollDate())==false
              ||this.clientNickname.equals(client.getClientNickname())==false|| this.clientTel.equals(client.getClientTel())==false
                      || this.clientPrivilege.equals(client.getClientPrivilege())==false
              //for正常登录
              || this.clientPassword.equals(client.getClientPassword())==false|| this.clientSex.equals(client.getClientSex())==false
                      ||(client.getClientAddress()==null||this.clientAddress.equals(client.getClientAddress())==false)
                      ||(client.getClientDescription()==null||this.clientDescription.equals(client.getClientDescription())==false)
/*                      ||(client.getClientAddress()==null||this.clientAddress.equals(client.getClientAddress())==false)
                      ||(client.getClientDescription()==null||this.clientDescription.equals(client.getClientDescription())==false) */
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


  public String getClientDescription() {
    return clientDescription;
  }

  public void setClientDescription(String clientDescription) {
    this.clientDescription = clientDescription;
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
