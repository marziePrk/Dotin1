package com.company;

import com.exceotion.InvalidDepositTypeException;
import com.exceotion.NegativeBalanceException;
import com.exceotion.NegativeDurationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Dotin school 6 on 6/26/2016.
 */
public class Deposit implements Comparable<Deposit> {
    private int customerNumber;
    private int interestRate;
    private BigDecimal depositBalance;
    private int durationInDays;
    BigDecimal payedInterest;
    private DepositType depositType;

    public Deposit(int customerNumber, String depositTypeString, BigDecimal depositBalance, int durationInDays) throws NegativeDurationException, NegativeBalanceException, IllegalAccessException, InstantiationException, InvalidDepositTypeException {
        setCustomerNumber(customerNumber);
        setDepositBalance(depositBalance);
        setDurationInDays(durationInDays);
        setDepositType(depositTypeString);
        setInterestRate();
        setPayedInterest();
        System.out.println(""+ customerNumber);
        System.out.println(depositTypeString);
        System.out.println(""+depositBalance);
        System.out.println(""+durationInDays);
        System.out.println(""+interestRate);

    }
    //getter
    public int getCustomerNumber() {
        return customerNumber;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public BigDecimal getPayedInterest() {
        return payedInterest;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    //setter

    public void setDepositType(String depositTypeString) throws InvalidDepositTypeException, IllegalAccessException, InstantiationException {
        Class depTypeClass = null;
        try {
            depTypeClass = Class.forName("com.company." + depositTypeString);
        } catch (ClassNotFoundException e) {
            throw new InvalidDepositTypeException("There are not this deposit type!");
        }
        this.depositType = (DepositType) depTypeClass.newInstance();
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setInterestRate() {
        this.interestRate = depositType.getInterestRate();
    }

    public void setDepositBalance(BigDecimal depositBalance) throws NegativeBalanceException {
        if (depositBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeBalanceException("deposit Balance is out of range");
        }
        this.depositBalance = depositBalance;
    }

    public void setDurationInDays(int durationInDays) throws NegativeDurationException {
        if (durationInDays<=0){
            throw new NegativeDurationException("Duration in days is out of range!");
        }
        this.durationInDays = durationInDays;
    }

    public void setPayedInterest() {
        this.payedInterest = computeInterest();
    }

    @Override
    public int compareTo(Deposit deposit) {
       /* int res = payedInterest.compareTo(deposit.payedInterest);
        if (res == 0) {
            return 0;
        } else if (res == 1) {
            return 1;
        } else
            return -1;*/
        return payedInterest.compareTo(deposit.payedInterest);
    }
    /* public Deposit(int customerNumber, String depositTypeString, int interestRate, BigDecimal depositBalance, int durationInDays) {
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
 */
    public BigDecimal computeInterest() {
        BigDecimal multiply = getDepositBalance().multiply(new BigDecimal(getDurationInDays() * getInterestRate()));
        payedInterest = multiply.divide(new BigDecimal(36500), 3, RoundingMode.CEILING);
        System.out.println("sud ="+ payedInterest);
        return payedInterest;
    }
}
