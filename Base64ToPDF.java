package com;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.bind.DatatypeConverter;
import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;
public class Base64ToPDF extends AbstractTransformation {
@Override
public void transform(TransformationInput in, TransformationOutput out)throws StreamTransformationException {
executeMapping(in.getInputPayload().getInputStream(), out.getOutputPayload().getOutputStream());
}

public void executeMapping(InputStream in, OutputStream out) throws StreamTransformationException {
String xmlString = "";
String XML_START_TAG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
XML_START_TAG += "<ns:UploadPlainDocumentWithFolder xmlns:ns=\"http://schemas.abc.com/xyz/2010/11\">";
String XML_END_TAG = "</ns:UploadPlainDocumentWithFolder>";

xmlString = xmlString + XML_START_TAG + "<PlainDoc><DocumentName>" + "YourDocName" + "</DocumentName>";
xmlString += "<DocumentURL>" + "http://yourURL" + "</DocumentURL>";

ByteArrayOutputStream buffer = new ByteArrayOutputStream();
try {
int nRead;
byte[] data = new byte[16384];
while ((nRead = in.read(data, 0, data.length)) != -1)
  buffer.write(data, 0, nRead);
buffer.flush();
}
catch (Exception e) {
throw new StreamTransformationException(e.getMessage());
}

xmlString += "<DocumentBytes>" + DatatypeConverter.printBase64Binary(buffer.toByteArray()) + "</DocumentBytes></PlainDoc>";
xmlString += "<DocLibURL>http://xyz</DocLibURL><ApplicationId>APPL_ID</ApplicationId><UserId>USR_ID</UserId>" + XML_END_TAG;

try {
out.write(xmlString.getBytes("UTF-8"));
}
catch (Exception e) {
throw new StreamTransformationException(e.getMessage());
}
}

public static void main(String[] args) {
try {
FileInputStream inFile = new FileInputStream("C:\\Users\\mkhavatkopp\\Desktop\\PI\\Test\\SHA2 Certificate Upgrade.pdf");
FileOutputStream outFile = new FileOutputStream("C:\\Users\\mkhavatkopp\\Desktop\\PI\\Test\\encoded.xml");
Base64ToPDF mapper = new Base64ToPDF();
mapper.executeMapping(inFile, outFile);

outFile.flush();
outFile.close();
}
catch (Exception e) {
e.printStackTrace();
}
}

}
