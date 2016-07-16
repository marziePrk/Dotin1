package com.company;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

/**
 * Created by Dotin school 6 on 6/28/2016.
 */
public class BankDepositInterestCalculator {
    public static void main(String[] args) {
        DepositInformation depositInformation = new DepositInformation();
        try {

            depositInformation.readDepositInformation();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        /*for (Deposit s : information.depositList) {
            s.payedInterest = s.computeInterest(s.getDepositBalance(), s.getDurationInDays(),s.getInterestRate());
            System.out.println("sud :" + s.payedInterest);
        }*/
        Collections.sort(depositInformation.depositList);
        Collections.reverse(depositInformation.depositList);
        depositInformation.createOutputFile();
        /*for (Deposit d : information.depositList){
            System.out.println(d.payedInterest);
        }*/
        //information.createOutPutFile(information.depositList);
    }


}

