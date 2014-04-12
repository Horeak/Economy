package com.eco.Economy.Lib;


import com.eco.Economy.Items.ModItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MoneyUtils {

    public static String MoneyMark = "$";
    public static String CurrencyName = "null";

    public static int Multiplier = 1;
    public static int StarterMoney = 1000;
    public static int MaxMoneyTransfer = 100000;

    public static int TextArea = 1;





    /**
     * Adds physical money to the players inventory
     *
     *
     * @param player The player that should receive the money
     * @param Amount The amount of money the player should receive max 100 000
     */
    public static void AddMoneyToPlayerInv(EntityPlayer player, int Amount){


        if(Amount > MaxMoneyTransfer)
            Amount = MaxMoneyTransfer;


        for(int i = 0; i < (Amount + "").length(); i++){

            String StringNumber = Integer.toString(Amount);
            int CurrentNumber = Integer.valueOf(StringNumber.substring(i, i + 1));

            int UseNum = CurrentNumber * ((i + 1) * 10);

            //System.out.println(GetMoneyStack(i * (10 * (i + 1))) + " : " + CurrentNumber + " : " + i);


            System.out.println(CurrentNumber + " : " + i);


              if (UseNum > 20 && UseNum < 100){
                int t = UseNum / 20;
                int j = (UseNum - t) / 10;
                  int k = UseNum - j;



                ItemStack stack_1 = new ItemStack(GetMoneyStack(20).getItem(), t);
                ItemStack stack_2 = new ItemStack(GetMoneyStack(10).getItem(), j);
                  ItemStack stack_3 = new ItemStack(GetMoneyStack(1).getItem(), k);

                  AddItemToPlayerInv(player, stack_1);
                  AddItemToPlayerInv(player, stack_2);
                  AddItemToPlayerInv(player, stack_3);


            }else if (UseNum > 9 && UseNum < 100){
                int t = UseNum / 10;
                int j = UseNum - t;



                ItemStack stack_1 = new ItemStack(GetMoneyStack(10).getItem(), t);
                ItemStack stack_2 = new ItemStack(GetMoneyStack(1).getItem(), j);

                  AddItemToPlayerInv(player, stack_1);
                  AddItemToPlayerInv(player, stack_2);


              } else if(CurrentNumber > 5 && UseNum < 10){
                      int t = CurrentNumber / 5;
                      int j = CurrentNumber - t;

                      ItemStack stack_1 = new ItemStack(GetMoneyStack(5).getItem(), t);
                      ItemStack stack_2 = new ItemStack(GetMoneyStack(1).getItem(), j);

                  AddItemToPlayerInv(player, stack_1);
                  AddItemToPlayerInv(player, stack_2);


            }else{


                ItemStack stack_1 = new ItemStack(GetMoneyStack(1).getItem(), CurrentNumber);

                  AddItemToPlayerInv(player, stack_1);
            }






           // ItemStack stack = new ItemStack(GetMoneyStack(i * (10 * (i))).getItem(), Integer.valueOf((Amount + "").substring(i, i + 1)));


          //  player.inventory.addItemStackToInventory(stack);





        }


    }


    public static void AddItemToPlayerInv(EntityPlayer player, ItemStack Stack){

        player.inventory.addItemStackToInventory(Stack);
        player.inventory.inventoryChanged = true;
    }


    public static ItemStack GetMoneyStack(int Amount){

        if(Amount == 1)
            return new ItemStack(ModItemRegistry.Coin1);

        if(Amount == 5)
            return new ItemStack(ModItemRegistry.Coin5);

        if(Amount == 10)
            return new ItemStack(ModItemRegistry.Coin10);

        if(Amount == 20)
            return new ItemStack(ModItemRegistry.Coin20);


        if(Amount == 50)
            return new ItemStack(ModItemRegistry.Bill50);

        if(Amount == 100)
            return new ItemStack(ModItemRegistry.Bill100);

        if(Amount == 200)
            return new ItemStack(ModItemRegistry.Bill200);

        if(Amount == 500)
            return new ItemStack(ModItemRegistry.Bill500);

        if(Amount == 1000)
            return new ItemStack(ModItemRegistry.Bill1000);

        return null;
    }

}
