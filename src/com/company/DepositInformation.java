package com.company;

import com.exceotion.InvalidDepositTypeException;
import com.exceotion.NegativeBalanceException;
import com.exceotion.NegativeDurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Dotin school 6 on 6/28/2016.
 */
public class DepositInformation {
    ArrayList<Deposit> depositList = new ArrayList<Deposit>();

    public void readDepositInformation() throws ParserConfigurationException, IOException, SAXException {
        File file = new File("resources//deposits");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document xmlDoc = documentBuilder.parse(file);
        xmlDoc.getDocumentElement().normalize();
        //System.out.println("Root Element : " + doc.getDocumentElement().getNodeName());
        NodeList nodelist = xmlDoc.getElementsByTagName("deposit");
        for (int counter = 0; counter < nodelist.getLength(); counter++) {
            Node node = nodelist.item(counter);
            System.out.println(node.getNodeName());
            //what is the meaning of next line?
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                //item(0)???????
                System.out.println(".........................................");
                int customerNumber=Integer.parseInt(element.getElementsByTagName("customerNumber").item(0).getTextContent());
                String depositType =element.getElementsByTagName("depositType").item(0).getTextContent();
                BigDecimal depositBalance =new BigDecimal(element.getElementsByTagName("depositBalance").item(0).getTextContent());
                int durationInDays = Integer.parseInt(element.getElementsByTagName("durationInDays").item(0).getTextContent());
                try {
                    Deposit deposit = new Deposit(customerNumber, depositType, depositBalance, durationInDays);
                    depositList.add(deposit);
                } catch (NegativeDurationException e) {
                    e.printStackTrace();
                    //e.getMessage();
                    System.out.println("Negative duration in days!");
                } catch (NegativeBalanceException e) {
                    e.printStackTrace();
                    //e.getMessage();
                    System.out.println("Negative Deposit balance!");
                } catch (InvalidDepositTypeException e) {
                    e.printStackTrace();
                    //e.getMessage();
                    System.out.println("Invalid this deposit type");
                }catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                //make on object with reflection
                //DepositType depositType = (DepositType) Class.forName("com.company." + depositType).newInstance();
                //int interestRate = depositType.getInterestRate();
                //deposit.payedInterest = deposit.computeInterest();
                //return null
                //depositList.add(deposit.setInfo(customerNumber,depositType,depositBalance,durationInDays));


                System.out.println(".........................................");

            }
        }
        System.out.println(depositList);
    }

    public void createOutputFile() {
        File finalFile = new File("resources//PayedInterest.txt");
        try {
            RandomAccessFile outputFile = new RandomAccessFile(finalFile, "rw");
            for (Deposit deposit : depositList) {
                outputFile.writeBytes(deposit.getCustomerNumber() + "#" + deposit.getPayedInterest());
                outputFile.writeBytes("\n");
                //randomAccessFile.seek(10);
            }
            outputFile.seek(0);
        } catch (IOException e) {
            e.printStackTrace();
            // System.out.println(e.getMessage());
        }
    }
   /* public void sort(ArrayList<Deposit> arrayList) {
        Collections.sort(arrayList, new Comparator<Deposit>() {
            @Override
            public int compare(Deposit deposit1, Deposit deposit2) {
                return deposit1.payedInterest.compareTo(deposit2.payedInterest);
            }
        });
    }*/

   /* public void createOutPutFile(ArrayList<Deposit> arrayList) throws IOException {
        File file = new File("outPutFile1.txt");
        FileWriter fileWriter = new FileWriter(file);
        for (Deposit s : arrayList) {
            try {
                fileWriter.write(s.getCustomerNumber()+"#" +s.getPayedInterest()+"\n");
                fileWriter.flush();
                //Exeption
                //fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
*/

}