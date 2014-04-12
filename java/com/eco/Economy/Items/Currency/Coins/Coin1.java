package com.eco.Economy.Items.Currency.Coins;

import com.eco.Economy.Items.Currency.CurrencyItem;
public class Coin1 extends CurrencyItem {

    @Override
    public int Value() {
        return 1;
    }

    @Override
    public String CurrencyType() {
        return "Coin";
    }
}
