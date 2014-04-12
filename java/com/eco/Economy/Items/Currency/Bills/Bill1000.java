package com.eco.Economy.Items.Currency.Bills;

import com.eco.Economy.Items.Currency.CurrencyItem;
public class Bill1000 extends CurrencyItem {


    @Override
    public int Value() {
        return 1000;
    }

    @Override
    public String CurrencyType() {
        return "Bill";
    }
}
