package com;
/*
 * Author : Manoj K (KHAVAMA1)
 * Date: 9th March 2017
 * Description : To convert the XMl to XLS file
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class JM_XML_TO_XLSX {

    public static void main(String argv[]) {

        ArrayList<String> firstNames = new ArrayList<String>();
        ArrayList<String> lastNames = new ArrayList<String>();
        ArrayList<String> ages = new ArrayList<String>();

        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = (Document) docBuilder.parse(new File("C:\\Users\\mkhavatkopp\\Desktop\\PI\\Test\\Input.xml"));

            // normalize text representation
            doc.getDocumentElement().normalize();
            System.out.println("Root element of the doc is :\" "+ doc.getDocumentElement().getNodeName() + "\"");
            NodeList listOfPersons = doc.getElementsByTagName("person");
            int totalPersons = listOfPersons.getLength();
            System.out.println("Total no of people : " + totalPersons);
            for (int s = 0; s < listOfPersons.getLength(); s++) 
            {
                Node firstPersonNode = listOfPersons.item(s);
                if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element firstPersonElement = (Element) firstPersonNode;
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("first");
                    Element firstNameElement = (Element) firstNameList.item(0);
                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.println("First Name : "+ ((Node) textFNList.item(0)).getNodeValue().trim());
                    firstNames.add(((Node) textFNList.item(0)).getNodeValue().trim());
                    NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
                    Element lastNameElement = (Element) lastNameList.item(0);
                    NodeList textLNList = lastNameElement.getChildNodes();
                    System.out.println("Last Name : "+ ((Node) textLNList.item(0)).getNodeValue().trim());
                    lastNames.add(((Node) textLNList.item(0)).getNodeValue().trim());
                    NodeList ageList = firstPersonElement.getElementsByTagName("age");
                    Element ageElement = (Element) ageList.item(0);
                    NodeList textAgeList = ageElement.getChildNodes();
                    System.out.println("Age : "+ ((Node) textAgeList.item(0)).getNodeValue().trim());
                    ages.add(((Node) textAgeList.item(0)).getNodeValue().trim());
                }// end of if clause

            }// end of for loop with s var
            for(String firstName:firstNames)
            {
                System.out.println("firstName : "+firstName);
            }
            for(String lastName:lastNames)
            {
                System.out.println("lastName : "+lastName);
            }
            for(String age:ages)
            {
                System.out.println("age : "+age);
            }

        } 
        catch (SAXParseException err) 
        {
            System.out.println("** Parsing error" + ", line "+ err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
        } 
        catch (SAXException e) 
        {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } 
        catch (Throwable t) 
        {
            t.printStackTrace();
        }


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sample sheet");

        Map<String, Object[]> data = new HashMap<String, Object[]>();
        for(int i=0;i<firstNames.size();i++)
        {
            data.put(i+"",new Object[]{firstNames.get(i),lastNames.get(i),ages.get(i)});
        }
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\mkhavatkopp\\Desktop\\PI\\Test\\book.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }// end of main

}