package com.sap.mapping.java;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class SetCounter 
{
public static void main(String[] args) 
{
	String msgID= "123456";
	String Const= "Map2";
	System.out.println(SetCounter.MsgID(msgID,Const));
}

public static String MsgID(String msgid,String Const)
{
String U_Counter="";
Properties properties = new Properties();
File propertiesfile = new File("D:/usr/sap/MsgId.txt");
try
{
propertiesfile.createNewFile();
properties.load(new FileInputStream(propertiesfile));
}
catch (IOException e)
{
e.printStackTrace();
return "Error:File not read";
}
String number = properties.getProperty(msgid);
if (number == null)
{
number = "0";
String counter = String.valueOf(Integer.parseInt(number) + 1);
properties.setProperty(msgid, counter);
U_Counter= getCounter(Const,"ADD");
}
U_Counter= getCounter(Const,"GET");
try
{
properties.store(new FileOutputStream(propertiesfile), null);
} catch (IOException e) {
return "Error:File not updated";
}
return U_Counter;
}
public static String getCounter(String Const , String opr)
{
String counter = "";
Properties properties = new Properties();
File propertiesfile = new File("D:/usr/sap/Counter.txt");
try
{
propertiesfile.createNewFile();
properties.load(new FileInputStream(propertiesfile));
}
catch (IOException e)
{
e.printStackTrace();
return "Error:File not read";
}
String number = properties.getProperty(Const);
if (number == null)
{
number = "0";
counter = String.valueOf(Integer.parseInt(number) + 1);
properties.setProperty(Const, counter);
}
else
{
	if(opr.equalsIgnoreCase("ADD"))
	{
		counter = String.valueOf(Integer.parseInt(number) + 1);
		properties.setProperty(Const, counter);		
	}
	else
	{
		counter = number;
	}
}
try
{
properties.store(new FileOutputStream(propertiesfile), null);
} catch (IOException e) {
return "Error:File not updated";
}
return counter;
}
}
		 
