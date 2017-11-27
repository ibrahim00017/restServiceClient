package com.bootcamp;

import com.bootcamp.client.AxeClient;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        AxeClient axeClient = new AxeClient();
        try {
            axeClient.findAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
