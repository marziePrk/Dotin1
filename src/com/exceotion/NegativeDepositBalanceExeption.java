package com.exceotion;

/**
 * Created by Dotin school 6 on 7/3/2016.
 */
public class NegativeDepositBalanceExeption extends Exception{
    //public NegativeDepositBalanceExeption(){ super();}
    public NegativeDepositBalanceExeption(String message){
        super(message);
    }

}
