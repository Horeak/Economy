package com.eco.Economy.Items.Currency.Bills;

import com.eco.Economy.Items.Currency.CurrencyItem;
public class Bill500 extends CurrencyItem {


    @Override
    public int Value() {
        return 500;
    }

    @Override
    public int CurrencyType() {
        return 1;
    }
}
