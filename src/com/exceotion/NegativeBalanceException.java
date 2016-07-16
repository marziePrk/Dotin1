package com.exceotion;

/**
 * Created by Dotin school 6 on 7/3/2016.
 */
public class NegativeBalanceException extends Exception{
    //public NegativeDepositBalanceExeption(){ super();}
    public NegativeBalanceException(String message){
        super(message);
    }

}
