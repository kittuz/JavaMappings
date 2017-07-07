import java.net.*;
import java.io.*;
public class CommChannelSwitcher {
 public String setCCState(String httpString) {
 String result = "";
 StringBuilder response = new StringBuilder();
 try {
 URL url = new URL(httpString);
 
 Authenticator.setDefault (new Authenticator() {
  protected PasswordAuthentication getPasswordAuthentication() {
  return new PasswordAuthentication ("userid", "password".toCharArray());
  }
 });
 
 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 conn.setRequestMethod("GET");
 BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
  String line;
  while ((line = rd.readLine()) != null)
  response.append(line);
  rd.close();
  result = response.toString();
 }
 catch (Exception e) {
 result = e.getMessage();
 }
  
 return result;
 }
 
 public static void main(String[] args) {
 String getString = "http://host:port/AdapterFramework/ChannelAdminServlet?party=&service=ARIBA_DEV00&channel=Ariba_Supplier_Profile_SOAP_Receiver&action=start";
 CommChannelSwitcher httpCaller = new CommChannelSwitcher();
 System.out.println(httpCaller.setCCState(getString));
 }
}