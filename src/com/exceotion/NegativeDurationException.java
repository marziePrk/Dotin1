package com.exceotion;

/**
 * Created by Dotin school 6 on 7/3/2016.
 */
public class NegativeDurationException extends Exception {
   //public NegativeDurationException( ){ super(); }
    public NegativeDurationException(String message) {
        super(message);
    }
}
