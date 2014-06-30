package com.eco.Economy.Items.Currency.Bills;

import com.eco.Economy.Items.Currency.CurrencyItem;

public class Bill50 extends CurrencyItem {
    @Override
    public int Value() {
        return 50;
    }

    @Override
    public int CurrencyType() {
        return 1;
    }
}
