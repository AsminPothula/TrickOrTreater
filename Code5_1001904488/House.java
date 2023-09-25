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


public abstract class House
{
    private String houseName;
    HashMap <String, Integer> CandyList = new HashMap<>();
    
    public House(String HouseName, HashMap <String, Integer> candyList )
    {
        houseName = HouseName;
        CandyList = candyList;
    }
    
    
    public abstract String ringDoorbell(TrickOrTreater TOT);
}
