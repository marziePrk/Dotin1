package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Dotin school 6 on 6/26/2016.
 */
public class Deposit implements Comparable {
    private int customerNumber;
    private int interestRate;
    private BigDecimal depositBalance;
    private int durationInDays;
    BigDecimal payedInterest;
    private String depositTypeString;

    public Deposit(int customerNumber, String depositTypeString, int interestRate, BigDecimal depositBalance, int durationInDays) {
        this.customerNumber = customerNumber;
        System.out.println("customerNumber =" + customerNumber);
        this.depositTypeString = depositTypeString;
        System.out.println("depositTypeString =" + this.depositTypeString);
        this.depositBalance = depositBalance;
        System.out.println("depositBalance =" +depositBalance);
        this.durationInDays = durationInDays;
        System.out.println("durationInDays =" +durationInDays);
        this.interestRate = interestRate;

    }

    public BigDecimal computeInterest() {
        payedInterest = depositBalance.multiply(new BigDecimal(durationInDays * interestRate));
        payedInterest = payedInterest.divide(new BigDecimal(36500), 3, RoundingMode.CEILING);
        System.out.println("sud ="+ payedInterest);
        return payedInterest;
    }



    public int getCustomerNumber() {
        return customerNumber;
    }

    public int getInterestRate(){ return interestRate; }

    public BigDecimal getDepositBalance(){ return depositBalance; }

    public int getDurationInDays(){ return durationInDays; }

    public BigDecimal getPayedInterest() { return payedInterest;    }

    @Override
    public int compareTo(Object object) {
        Deposit deposit = (Deposit) object;
        int res = payedInterest.compareTo(deposit.payedInterest);
        if (res == 0) {
            return 0;
        } else if (res == 1) {
            return 1;
        } else
            return -1;
    }
}
