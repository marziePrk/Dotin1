package com.company;

public abstract class DepositType {

    public abstract int getInterestRate();

    //public void setInterestRate(int interestRate){this.interestRate=interestRate;


    //public abstract int getInterestRate();
    //        Object object = Class.forName("com.company." + depositType).newInstance();
//        Method method = object.getClass().getDeclaredMethod("getInterestRate");
//        interestRate = (Integer) method.invoke(object);
//        return interestRate;

  /*  public  int getInterestRate(String depositBalance){
        if (depositBalance.equals("ShortTerm")){
            ShortTerm shortTerm = new ShortTerm();
            interestRate=shortTerm.getInterestRate();
        }else if (depositBalance.equals("Qarz")){
            Qarz qarz = new Qarz();
            interestRate=qarz.getInterestRate();
        }else if (depositBalance.equals("LongTerm")){
            LongTerm longTerm=new LongTerm();
            interestRate=longTerm.getInterestRate();
        }
        return interestRate;
    }
*/

}
