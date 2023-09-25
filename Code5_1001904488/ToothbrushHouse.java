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



public class ToothbrushHouse extends House
{
    ToothbrushHouse(String HouseName, HashMap <String, Integer> CandyList)
    {
        super(HouseName, CandyList);
        
    }
    
    public synchronized String ringDoorbell(TrickOrTreater TOT)
    {
        TOT.addToPath("-");
        
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        
        return "Toothbrush";
    }
    
}

