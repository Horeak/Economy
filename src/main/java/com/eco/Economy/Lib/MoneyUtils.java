package com.eco.Economy.Lib;


import com.eco.Economy.Items.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

import java.util.*;

public class MoneyUtils {

    public static String MoneyMark = "$";
    public static String CurrencyName = "null";

    public static int Multiplier = 1;
    public static int StarterMoney = 1000;
    public static int MaxMoneyTransfer = 100000;
    public static int MaxPinLength = 4;

    public static int TextArea = 1;



    public static String GetCreditCardOwner(ItemStack stack){

        if(stack.getTagCompound() != null){
            if(stack.getTagCompound().getString("CardOwner") != null && !stack.getTagCompound().getString("CardOwner").equalsIgnoreCase(""))
            return stack.getTagCompound().getString("CardOwner");
        }

        return "";
    }

    public static String GetCreditCardPin(ItemStack stack){

        if(stack.getTagCompound() != null){
            if(stack.getTagCompound().getString("PinCode") != null && !stack.getTagCompound().getString("PinCode").equalsIgnoreCase(""))
                return stack.getTagCompound().getString("PinCode");
        }

        return "";
    }


    public static int GenerateRandomPin(){
        int t = 0;
        Random random = new Random();
        for(int i = 0; i < MaxPinLength-1; i++){
            t += random.nextInt(9);
            t *= 10;
        }
            return t;
    }


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



        int Current;

       Current = GetMoneyAmount(Amount, 1000);
        if(Current > 0){
            AddItem(player, new ItemStack(GetMoneyStack(1000).getItem(), Current));
            Amount -= (1000 * Current);

        }else{

            Current = GetMoneyAmount(Amount, 500);

            if(Current > 0){
                AddItem(player, new ItemStack(GetMoneyStack(500).getItem(), Current));
                Amount -= (500 * Current);

            }else{

                Current = GetMoneyAmount(Amount, 200);

                if(Current > 0){
                    AddItem(player, new ItemStack(GetMoneyStack(200).getItem(), Current));
                    Amount -= (200 * Current);


            }else{

                Current = GetMoneyAmount(Amount, 100);

                if(Current > 0){
                    AddItem(player, new ItemStack(GetMoneyStack(100).getItem(), Current));
                    Amount -= (100 * Current);

                }else{

                    Current = GetMoneyAmount(Amount, 50);

                    if(Current > 0){
                        AddItem(player, new ItemStack(GetMoneyStack(50).getItem(), Current));
                        Amount -= (50 * Current);

                    }else{

                        Current = GetMoneyAmount(Amount, 20);

                        if(Current > 0){
                            AddItem(player, new ItemStack(GetMoneyStack(20).getItem(), Current));
                            Amount -= (20 * Current);

                        }else{

                            Current = GetMoneyAmount(Amount, 10);

                            if(Current > 0){
                                AddItem(player, new ItemStack(GetMoneyStack(10).getItem(), Current));
                                Amount -= (10 * Current);

                            }else{

                                Current = GetMoneyAmount(Amount, 5);

                                if(Current > 0){
                                    AddItem(player, new ItemStack(GetMoneyStack(5).getItem(), Current));
                                    Amount -= (5 * Current);

                                }else{

                                    Current = GetMoneyAmount(Amount, 1);

                                    if(Current > 0){
                                        AddItem(player, new ItemStack(GetMoneyStack(1).getItem(), Current));
                                         Amount -= Current;

                                    }}}}}}}}}


            Current = GetMoneyAmount(Amount, 500);

            if(Current > 0){
                AddItem(player, new ItemStack(GetMoneyStack(500).getItem(), Current));
                Amount -= (500 * Current);

            }else{

                Current = GetMoneyAmount(Amount, 200);

                if(Current > 0){
                    AddItem(player, new ItemStack(GetMoneyStack(200).getItem(), Current));
                    Amount -= (200 * Current);


                }else{

                    Current = GetMoneyAmount(Amount, 100);

                    if(Current > 0){
                        AddItem(player, new ItemStack(GetMoneyStack(100).getItem(), Current));
                        Amount -= (100 * Current);

                    }else{

                        Current = GetMoneyAmount(Amount, 50);

                        if(Current > 0){
                            AddItem(player, new ItemStack(GetMoneyStack(50).getItem(), Current));
                            Amount -= (50 * Current);

                        }else{

                            Current = GetMoneyAmount(Amount, 20);

                            if(Current > 0){
                                AddItem(player, new ItemStack(GetMoneyStack(20).getItem(), Current));
                                Amount -= (20 * Current);

                            }else{

                                Current = GetMoneyAmount(Amount, 10);

                                if(Current > 0){
                                    AddItem(player, new ItemStack(GetMoneyStack(10).getItem(), Current));
                                    Amount -= (10 * Current);

                                }else{

                                    Current = GetMoneyAmount(Amount, 5);

                                    if(Current > 0){
                                        AddItem(player, new ItemStack(GetMoneyStack(5).getItem(), Current));
                                        Amount -= (5 * Current);

                                    }else{

                                        Current = GetMoneyAmount(Amount, 1);

                                        if(Current > 0){
                                            AddItem(player, new ItemStack(GetMoneyStack(1).getItem(), Current));
                                             Amount -= Current;

                                        }}}}}}}}



                Current = GetMoneyAmount(Amount, 200);

                if(Current > 0){
                    AddItem(player, new ItemStack(GetMoneyStack(200).getItem(), Current));
                    Amount -= (200 * Current);


                }else{

                    Current = GetMoneyAmount(Amount, 100);

                    if(Current > 0){
                        AddItem(player, new ItemStack(GetMoneyStack(100).getItem(), Current));
                        Amount -= (100 * Current);

                    }else{

                        Current = GetMoneyAmount(Amount, 50);

                        if(Current > 0){
                            AddItem(player, new ItemStack(GetMoneyStack(50).getItem(), Current));
                            Amount -= (50 * Current);

                        }else{

                            Current = GetMoneyAmount(Amount, 20);

                            if(Current > 0){
                                AddItem(player, new ItemStack(GetMoneyStack(20).getItem(), Current));
                                Amount -= (20 * Current);

                            }else{

                                Current = GetMoneyAmount(Amount, 10);

                                if(Current > 0){
                                    AddItem(player, new ItemStack(GetMoneyStack(10).getItem(), Current));
                                    Amount -= (10 * Current);

                                }else{

                                    Current = GetMoneyAmount(Amount, 5);

                                    if(Current > 0){
                                        AddItem(player, new ItemStack(GetMoneyStack(5).getItem(), Current));
                                        Amount -= (5 * Current);

                                    }else{

                                        Current = GetMoneyAmount(Amount, 1);

                                        if(Current > 0){
                                            AddItem(player, new ItemStack(GetMoneyStack(1).getItem(), Current));
                                             Amount -= Current;

                                        }}}}}}}




                    Current = GetMoneyAmount(Amount, 100);

                    if(Current > 0){
                        AddItem(player, new ItemStack(GetMoneyStack(100).getItem(), Current));
                        Amount -= (100 * Current);

                    }else{

                        Current = GetMoneyAmount(Amount, 50);

                        if(Current > 0){
                            AddItem(player, new ItemStack(GetMoneyStack(50).getItem(), Current));
                            Amount -= (50 * Current);

                        }else{

                            Current = GetMoneyAmount(Amount, 20);

                            if(Current > 0){
                                AddItem(player, new ItemStack(GetMoneyStack(20).getItem(), Current));
                                Amount -= (20 * Current);

                            }else{

                                Current = GetMoneyAmount(Amount, 10);

                                if(Current > 0){
                                    AddItem(player, new ItemStack(GetMoneyStack(10).getItem(), Current));
                                    Amount -= (10 * Current);

                                }else{

                                    Current = GetMoneyAmount(Amount, 5);

                                    if(Current > 0){
                                        AddItem(player, new ItemStack(GetMoneyStack(5).getItem(), Current));
                                        Amount -= (5 * Current);

                                    }else{

                                        Current = GetMoneyAmount(Amount, 1);

                                        if(Current > 0){
                                            AddItem(player, new ItemStack(GetMoneyStack(1).getItem(), Current));
                                             Amount -= Current;

                                        }}}}}}


                        Current = GetMoneyAmount(Amount, 50);

                        if(Current > 0){
                            AddItem(player, new ItemStack(GetMoneyStack(50).getItem(), Current));
                            Amount -= (50 * Current);

                        }else{

                            Current = GetMoneyAmount(Amount, 20);

                            if(Current > 0){
                                AddItem(player, new ItemStack(GetMoneyStack(20).getItem(), Current));
                                Amount -= (20 * Current);

                            }else{

                                Current = GetMoneyAmount(Amount, 10);

                                if(Current > 0){
                                    AddItem(player, new ItemStack(GetMoneyStack(10).getItem(), Current));
                                    Amount -= (10 * Current);

                                }else{

                                    Current = GetMoneyAmount(Amount, 5);

                                    if(Current > 0){
                                        AddItem(player, new ItemStack(GetMoneyStack(5).getItem(), Current));
                                        Amount -= (5 * Current);

                                    }else{

                                        Current = GetMoneyAmount(Amount, 1);

                                        if(Current > 0){
                                            AddItem(player, new ItemStack(GetMoneyStack(1).getItem(), Current));
                                             Amount -= Current;

                                        }}}}}



                            Current = GetMoneyAmount(Amount, 20);

                            if(Current > 0){
                                AddItem(player, new ItemStack(GetMoneyStack(20).getItem(), Current));
                                Amount -= (20 * Current);

                            }else{

                                Current = GetMoneyAmount(Amount, 10);

                                if(Current > 0){
                                    AddItem(player, new ItemStack(GetMoneyStack(10).getItem(), Current));
                                    Amount -= (10 * Current);

                                }else{

                                    Current = GetMoneyAmount(Amount, 5);

                                    if(Current > 0){
                                        AddItem(player, new ItemStack(GetMoneyStack(5).getItem(), Current));
                                        Amount -= (5 * Current);

                                    }else{

                                        Current = GetMoneyAmount(Amount, 1);

                                        if(Current > 0){
                                            AddItem(player, new ItemStack(GetMoneyStack(1).getItem(), Current));
                                             Amount -= Current;

                                        }}}}




                                Current = GetMoneyAmount(Amount, 10);

                                if(Current > 0){
                                    AddItem(player, new ItemStack(GetMoneyStack(10).getItem(), Current));
                                    Amount -= (10 * Current);

                                }else{

                                    Current = GetMoneyAmount(Amount, 5);

                                    if(Current > 0){
                                        AddItem(player, new ItemStack(GetMoneyStack(5).getItem(), Current));
                                        Amount -= (5 * Current);

                                    }else{

                                        Current = GetMoneyAmount(Amount, 1);

                                        if(Current > 0){
                                            AddItem(player, new ItemStack(GetMoneyStack(1).getItem(), Current));
                                             Amount -= Current;

                                        }}}



                                    Current = GetMoneyAmount(Amount, 5);

                                    if(Current > 0){
                                        AddItem(player, new ItemStack(GetMoneyStack(5).getItem(), Current));
                                        Amount -= (5 * Current);

                                    }else{

                                        Current = GetMoneyAmount(Amount, 1);

                                        if(Current > 0){
                                            AddItem(player, new ItemStack(GetMoneyStack(1).getItem(), Current));
                                             Amount -= Current;

                                        }}




                                    Current = GetMoneyAmount(Amount, 1);


                                     if(Current > 0){
                                         AddItem(player, new ItemStack(GetMoneyStack(1).getItem(), Current));
                                         Amount -= Current;

                                          }






    }



    private static void AddItem(EntityPlayer player, ItemStack Stack){

        player.inventory.addItemStackToInventory(Stack);
        player.inventory.inventoryChanged = true;
        player.inventoryContainer.detectAndSendChanges();
    }



    private static int GetMoneyAmount(int amount, int CheckAmount){


        if(amount >= CheckAmount){

        int left = amount / CheckAmount;

            if(left > 0){
                return left;
            }


        }


        return 0;
    }

    private static ItemStack GetMoneyStack(int Amount){

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
