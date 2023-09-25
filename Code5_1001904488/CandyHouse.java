/*
 *  Asmin Pothula 1001904488
 */
package code5_1001904488;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.File;
import java.io.FileNotFoundException;

public class CandyHouse extends House
{
    public CandyHouse(String HouseName, HashMap <String, Integer> CandyList)
    {
        super(HouseName, CandyList);
        
    }
    
    public synchronized String ringDoorbell (TrickOrTreater TOT)
    {
        String Candy = null;
        TOT.addToPath("+");
        
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
           Thread.currentThread().interrupt(); 
        }
                
        Random rand = new Random();
        int random = rand.nextInt(CandyList.size()-1)+1;
         
        /* use an enhanced for loop to loop over the CandyList – Week12 – Slides 72-76 */ 
        for (HashMap.Entry bucketElement : CandyList.entrySet())
        {
            /* if the iterator’s value equals the chosen random number */
            /* then set Candy equal to key from the iterator */ 
            if ((int)bucketElement.getValue() == random)
            {
                Candy = (String)bucketElement.getKey();
            }
        }
        
        return Candy;
    }
}
