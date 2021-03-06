package example;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.sap.aii.mapping.api.*;


import java.io.*;
import java.util.Scanner;


public class XMLGenerator extends AbstractTransformation {


	@Override
	public void transform(TransformationInput transformationInput, TransformationOutput transformationOutput)
			throws StreamTransformationException {


		try {


			InputStream inputstream = transformationInput.getInputPayload().getInputStream();
			OutputStream outputstream = transformationOutput.getOutputPayload().getOutputStream();
			DocumentBuilderFactory document = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = ((DocumentBuilderFactory) document).newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			BufferedReader inpfile = new BufferedReader(new InputStreamReader(inputstream));
			doc.setXmlStandalone(true);
			Element docRoot = doc.createElement("ns0:Example");
			docRoot.setAttribute("xmlns:ns0", "http://example/test");
			doc.appendChild(docRoot);


			Element rootElement = doc.createElement("root");
			docRoot.appendChild(rootElement);


			String line;
			int i = 0;


			while ((line = inpfile.readLine()) != null)


			{
				if (i >= 21) {
					if (line.contains("END-OF-DATA")) {
						break;
					}
					
					Scanner rd = new Scanner(line);
										
					while (rd.hasNext()) {
						Element item = doc.createElement("item");
						rootElement.appendChild(item);


						Element input = doc.createElement("input");
						input.appendChild(doc.createTextNode(line));
						item.appendChild(input);						
						
						break;
					}
				}
				i++;
			}


			TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc),
					new StreamResult(outputstream));
			


		} catch (Exception exception) {
			getTrace().addDebugMessage(exception.getMessage());
			throw new StreamTransformationException(exception.toString());
		}


	}


}

//https://answers.sap.com/questions/352212/remove-header-and-footer-from-sender-file.html?childToView=352345#answer-352345
