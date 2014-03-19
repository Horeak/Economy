package com.eco.Economy.Proxies;


import com.eco.Economy.Tick.ClientTickHandler;

public class ClientProxy extends ServerProxy{


    public void RegisterClientTick(){
        tickHandlerClient = new ClientTickHandler();


    }

}
