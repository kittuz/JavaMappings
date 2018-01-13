import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONObject;
import org.json.XML;

import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;
public class JM_JSON_TO_XML extends AbstractTransformation {
@Override

  public void transform(TransformationInput transformationInput, TransformationOutput transformationOutput)
  throws StreamTransformationException 
  {
  InputStream inputStream = transformationInput.getInputPayload().getInputStream();
  OutputStream outputStream = transformationOutput.getOutputPayload().getOutputStream();
  executeMapping(inputStream,outputStream);
  }
  
  public void executeMapping(InputStream inputStream, OutputStream outputStream) throws StreamTransformationException
  {
	  try {
		  byte[] buf = new byte[inputStream.available()];
		  inputStream.read(buf);
		  //JSONObject xmlJsonObj = XML.toJSONObject(new String(buf, "utf-8"));
		  //String jsonPrettyPrintString = xmlJsonObj.toString(4);
		  JSONObject jsonFileObject = new JSONObject(new String(buf,"utf-8"));
	      String xml = XML.toString(jsonFileObject);
	      String finalStruct ="<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n<root>"+xml+"</root>";
		  byte[] bytes = finalStruct.toString().getBytes("UTF-8");
		  outputStream.write(bytes);
		  } catch (Exception e) {
		  getTrace().addDebugMessage("Exception while writing OutputPayload: IOException", e);
		  throw new StreamTransformationException(e.toString());
		  }  
  }
  public static void main(String[] args) {
	  try {
	  FileInputStream inFile = new FileInputStream("C:\\Users\\mkhavatkopp\\Desktop\\PI\\Test\\Json.txt");
	  FileOutputStream outFile = new FileOutputStream("C:\\Users\\mkhavatkopp\\Desktop\\PI\\Test\\converted_xml.xml");
	  JM_JSON_TO_XML mapper = new JM_JSON_TO_XML();
	  mapper.executeMapping(inFile, outFile);

	  outFile.flush();
	  outFile.close();
	  }
	  catch (Exception e) {
	  e.printStackTrace();
	  }
	  }
  
}
