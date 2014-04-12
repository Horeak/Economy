package com.eco.Economy.Items.Currency.Bills;

import com.eco.Economy.Items.Currency.CurrencyItem;

public class Bill100 extends CurrencyItem {


    @Override
    public int Value() {
        return 100;
    }

    @Override
    public String CurrencyType() {
        return "Bill";
    }
}
