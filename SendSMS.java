package com;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class SendSMS {
  // Find your Account Sid and Token at twilio.com/user/account
  public static final String VERSION = "7.12.0";
  public static final String JAVA_VERSION = System.getProperty("java.version");
  public static final String ACCOUNT_SID = "AC4104ba41280a8a6c467a13a935482f5b";
  public static final String AUTH_TOKEN = "7c0e30997c1c7803918c50538827c329";
  public static void main(String[] args)
  {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
    		new PhoneNumber("+917032408622"), 
    		new PhoneNumber("+15005550006"),
    		"This is Manoj Testing SMS API").setMediaUrl("https://climacons.herokuapp.com/clear.png").
    		create();
    System.out.println(message.getSid());
  }
}