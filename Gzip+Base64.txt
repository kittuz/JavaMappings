For Compressing :

import :
sun.misc.BASE64Encoder
java.util.zip.GZIPInputStream
java.util.zip.GZIPOutputStream


String encoded = "";
try{
ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
GZIPOutputStream gzip = new GZIPOutputStream(baos);
BASE64Encoder encoder = new BASE64Encoder();
gzip.write(invoice.getBytes());
 gzip.close();     
 encoded = encoder.encode( baos.toByteArray());
}
catch(Exception e){
return "";
}  

   return encoded;


For Decompressing:

String line="";
StringBuilder sb = new StringBuilder();
try {

BASE64Decoder decoder = new BASE64Decoder();
byte b[] = decoder.decodeBuffer(encoded);
ByteArrayInputStream bis = new ByteArrayInputStream(b);
GZIPInputStream gis = new GZIPInputStream(bis);
BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
while((line = br.readLine()) != null) {
sb.append(line);
}
}
catch (IOException e) {
 System.out.println(e);
}
return sb.toString();