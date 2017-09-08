import java.io.InputStream;
import java.io.OutputStream;
import org.json.JSONObject;
import org.json.XML;
import com.sap.aii.mapping.api.AbstractTransformation;
import com.sap.aii.mapping.api.StreamTransformationException;
import com.sap.aii.mapping.api.TransformationInput;
import com.sap.aii.mapping.api.TransformationOutput;
public class XMLToJSONJavaMap extends AbstractTransformation {
  @Override
  public void transform(TransformationInput transformationInput, TransformationOutput transformationOutput)
  throws StreamTransformationException {
  InputStream inputStream = transformationInput.getInputPayload().getInputStream();
  OutputStream outputStream = transformationOutput.getOutputPayload().getOutputStream();
  try {
  byte[] buf = new byte[inputStream.available()];
  inputStream.read(buf);
  JSONObject xmlJsonObj = XML.toJSONObject(new String(buf, "utf-8"));
  String jsonPrettyPrintString = xmlJsonObj.toString(4);
  byte[] bytes = jsonPrettyPrintString.toString().getBytes("UTF-8");
  outputStream.write(bytes);
  } catch (Exception e) {
  getTrace().addDebugMessage("Exception while writing OutputPayload: IOException", e);
  throw new StreamTransformationException(e.toString());
  }
  }
}
