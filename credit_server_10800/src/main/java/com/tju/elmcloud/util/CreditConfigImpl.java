package com.tju.elmcloud.util;

public class CreditConfigImpl implements CreditConfig{
    @Override
    public int calculateCreditByPrice(double price) {
        int credit = (int) (price / moneyForOneCredit);
        return credit;
    }
}
