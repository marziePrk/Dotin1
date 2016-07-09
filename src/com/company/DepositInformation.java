package com.company;

import com.exceotion.NegativeDepositBalanceExeption;
import com.exceotion.NegativeDurationException;
import com.exceotion.UnDefinedDepositTypeExeption;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Dotin school 6 on 6/28/2016.
 */
public class DepositInformation {
    ArrayList<Deposit> depositList = new ArrayList<Deposit>();

    public DepositInformation() {
        try {
            File file = new File("deposits");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            //System.out.println("Root Element : " + doc.getDocumentElement().getNodeName());
            NodeList nlist = doc.getElementsByTagName("deposit");
            for (int counter = 0; counter < nlist.getLength(); counter++) {
                Node node = nlist.item(counter);
                System.out.println(node.getNodeName());
                try {
                    //what is the meaning of next line?
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        //item(0)???????
                        System.out.println(".........................................");
                        int customerNumber = Integer.parseInt(element.getElementsByTagName("customerNumber").item(0).getTextContent());
                        String depositTypeString = element.getElementsByTagName("depositType").item(0).getTextContent();
                        checkDepositType(depositTypeString);
                        BigDecimal depositBalance = new BigDecimal(element.getElementsByTagName("depositBalance").item(0).getTextContent());
                        checkDepositBalance(depositBalance);
                        int durationInDays = Integer.parseInt(element.getElementsByTagName("durationInDays").item(0).getTextContent());
                        checkDurationInDays(durationInDays);
                        //make on object with reflection
                        DepositType depositType = (DepositType) Class.forName("com.company." + depositTypeString).newInstance();
                        int interestRate = depositType.getInterestRate();
                        Deposit deposit = new Deposit(customerNumber, depositTypeString, interestRate, depositBalance, durationInDays);
                        depositList.add(deposit);
                        deposit.payedInterest = deposit.computeInterest();
                        //return null
                        //depositList.add(deposit.setInfo(customerNumber,depositType,depositBalance,durationInDays));
                    }
                }catch (UnDefinedDepositTypeExeption unDefinedDepositType) {
                    System.out.println("There are not this Type");
                    //unDefinedDepositTypeExeption.printStackTrace();
                    unDefinedDepositType.getMessage();
                    continue;
                }catch (NegativeDepositBalanceExeption depositBalanceExeption) {
                    System.out.println("depositBalance is out of range");
                    //depositBalanceExeption.printStackTrace();
                    depositBalanceExeption.getMessage();
                    continue;
                }catch (NegativeDurationException negativeDuration) {
                    System.out.println("durationInDays is out of range");
                    //negativeDuration.printStackTrace();
                    negativeDuration.getMessage();
                    continue;
                }
            }
            System.out.println(".........................................");
            System.out.println(depositList);
        }catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
    }
    private void checkDepositType(String depositType) throws UnDefinedDepositTypeExeption {
        if (!(depositType.equals("ShortTerm") || depositType.equals("LongTerm") || depositType.equals("Qarz")))
            throw new UnDefinedDepositTypeExeption("Bad Deposit type!");
    }

    private void checkDepositBalance(BigDecimal bigDecimal) throws NegativeDepositBalanceExeption {
        if (bigDecimal.compareTo(BigDecimal.ZERO) < 0)
            throw new NegativeDepositBalanceExeption("deposit Balance is out of range");
    }
    private void checkDurationInDays(int durationInDays) throws NegativeDurationException {
        if (durationInDays <= 0)
            throw new NegativeDurationException("durationInDays is out of range");
    }
    public void randomAccessFile() {
        File file = new File("OutPutFile.txt");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            for (Deposit depositCounter : depositList) {
                randomAccessFile.writeUTF(depositCounter.getCustomerNumber() + "#" + depositCounter.getPayedInterest() + "\n");
                //randomAccessFile.seek(10);
            }
        } catch (FileNotFoundException fileNotFoundExeption) {
            fileNotFoundExeption.printStackTrace();
            // System.out.println(e.getMessage());
        } catch (IOException ioExeption) {
            ioExeption.printStackTrace();
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