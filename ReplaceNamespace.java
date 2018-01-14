import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;
import com.sap.aii.mapping.api.StreamTransformation;
import com.sap.aii.mapping.api.AbstractTrace;
import com.sap.aii.mapping.api.StreamTransformationException;
import java.util.HashMap;
@SuppressWarnings("deprecation")
public class JM_ReplaceNamespace implements StreamTransformation{
	private Map param;
	private AbstractTrace trace = null;
	public void setParameter(Map param) {
		this.param = param;
		if (param == null) {
			this.param = new HashMap();
		}

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)  {
		try { 
			FileInputStream fin = new FileInputStream ("C:\\Users\\mkhavatkopp\\Desktop\\PI\\Test\\Old.xml"); //INPUT FILE (PAYLOAD) 
			FileOutputStream fout = new FileOutputStream ("C:\\Users\\mkhavatkopp\\Desktop\\PI\\Test\\New.xml"); //OUTPUT FILE (PAYLOAD) 
			JM_ReplaceNamespace newObj = new JM_ReplaceNamespace (); 
			newObj.execute(fin, fout); 
		} 
		catch (Exception e1) { 
			e1.printStackTrace(); 
		} 

	}
	
	public void execute(InputStream inputStream, OutputStream outputStream)throws StreamTransformationException {
		StringBuffer sb = new StringBuffer();
		String line = null;
		String old = "urn:Ariba:Buyer:vrealm_2407";
		String New = "urn:Ariba:Buyer:vsap";
	

		try
		{

		   InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
		   BufferedReader br = new BufferedReader(isr);
		   while((line = br.readLine())!=null){
			   	  line = line.replaceAll("urn:Ariba:Buyer:vrealm_2407", "urn:Ariba:Buyer:vsap");
		          
		          sb.append(line);

		}
			
		outputStream.write(sb.toString().getBytes());
		outputStream.close();

		}

		catch(IOException e)
		{
		  e.printStackTrace();

		}

	}


}
