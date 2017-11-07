package com;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import sun.misc.BASE64Decoder;
public class PdftoBase64 {
public static void main(String[] args) {
try {
FileInputStream in = new FileInputStream("C:\\Users\\mkhavatkopp\\Desktop\\PI\\Test\\encoded.txt");
FileOutputStream out = new FileOutputStream("C:\\Users\\mkhavatkopp\\Desktop\\PI\\Test\\decoded.pdf");
ByteArrayOutputStream buffer = new ByteArrayOutputStream();
try
{
int rd;
byte[] data = new byte[16384];
while ((rd = in.read(data, 0, data.length)) != -1)
buffer.write(data, 0, rd);
buffer.flush();
System.out.println(buffer);
BASE64Decoder decoder = new BASE64Decoder();
byte[] decodedBytes = decoder.decodeBuffer(buffer.toString());
out.write(decodedBytes);
out.close();
}

catch (Exception e)
{
System.out.println((e.getMessage()));
}

}
catch (Exception e)
{
System.out.println((e.getMessage()));
}
}
}
