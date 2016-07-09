package com.company;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

/**
 * Created by Dotin school 6 on 6/28/2016.
 */
public class Main{
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DepositInformation depositInformation = new DepositInformation();
        /*for (Deposit s : information.depositList) {
            s.payedInterest = s.computeInterest(s.getDepositBalance(), s.getDurationInDays(),s.getInterestRate());
            System.out.println("sud :" + s.payedInterest);
        }*/
        Collections.sort(depositInformation.depositList);
        Collections.reverse(depositInformation.depositList);
        depositInformation.randomAccessFile();
        /*for (Deposit d : information.depositList){
            System.out.println(d.payedInterest);
        }*/
        //information.createOutPutFile(information.depositList);
    }


}

