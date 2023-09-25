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


public class TrickOrTreater implements Runnable
{
    public static int ImDone;
    private String name;
    private String path = ".";
    private ArrayList<House>ListOfHouses = new ArrayList<>(); 
    private HashMap<String, Integer>Bucket = new HashMap<>();
    
    public TrickOrTreater(String name, ArrayList <House> ListOfHouses)
    {
        this.name = name;
        this.ListOfHouses = ListOfHouses;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getPath()
    {
        return path;
    }
    
    /* void instance method addToPath that accepts a string that it concatenates */
    /* onto instance variable path*/
    public void addToPath(String concatStr)
    {
        path += concatStr;
    }
    
    
    private void Walk(int speed)
    {
        for (int i = 0; i < 10 ; i++)
        {
            //concatenate . onto path 
            // sleep for speed number of milliseconds
            path = path + ".";
            
            try
            {
                Thread.sleep(speed);
            } 
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public String printBucket()
    {
        String Candy = "";
        String BucketContents = "";
        int CandyCount = 0;
        
        BucketContents = String.format("%-10s - ", name);
        
        /* use an enhanced for loop to loop over the CandyList – Week12 – Slides 72-76 */ 
        for (HashMap.Entry bucketElement : Bucket.entrySet())
        {
            /* if the iterator’s value equals the chosen random number */
            Candy = (String)bucketElement.getKey();
            /* then set Candy equal to key from the iterator */ 
            CandyCount = (int)bucketElement.getValue();
            BucketContents += String.format("%15s %d ", Candy, CandyCount);
        }
        
        BucketContents += "\n"; 
        return BucketContents;
    }
    
    //override run() from Runnable; 
    public void run()
    {
        //System.out.printf("%s is going to sleep for %d milliseconds\n",name, sleepTime);
        //Thread.sleep(sleepTime);
        int counter = 0, speed = 0;
        String Candy;
        Random rand = new Random();
        
        for(House it : ListOfHouses)
        {
            speed = rand.nextInt(1501) + 1;
            Walk(speed);
            
            Candy = it.ringDoorbell(this);
            
            if (Bucket.containsKey(Candy))
            {
                counter = Bucket.get(Candy);
                Bucket.put(Candy, ++counter);
            }
            else
            {
                Bucket.put(Candy, 1);
            }
        }
        
        synchronized(this)
        { 
            ImDone++;
        }
    }
}

