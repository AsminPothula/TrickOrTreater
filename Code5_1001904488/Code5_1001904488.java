/*
 *  Asmin Pothula 1001904488 Coding Assignment 5
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

public class Code5_1001904488
{

    public static void CreateCandyList (String Candyfilename, HashMap <String, Integer> CandyList) throws FileNotFoundException
    {
        File FH = new File (Candyfilename);
        Scanner iFH = null;
        String element = null;
        
        try
        {
            iFH = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.printf("\n%s file name does not exist...exiting\n", Candyfilename);
            System.exit(0);
        }
        
        while(iFH.hasNextLine())
        {
            element = iFH.nextLine();
            String FileLine[] = element.split("[|]"); 
            element = FileLine[0];
            
            CandyList.put(element, Integer.parseInt(FileLine[1]));
           
        }
        
        iFH.close();
    }
    
    public static String CreateHouses (String Housefilename, ArrayList <House> Houses, HashMap <String, Integer> CandyList)
    {
        String HouseHeading = "           ";
        
        File FH = new File (Housefilename);
        Scanner iFH = null;
        
        try
        {
            iFH = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.printf("\n%s file name does not exist...exiting\n", Housefilename);
            System.exit(0);
        }
        
        //String list = " ";
        while(iFH.hasNextLine())
        {
            //list = iFH.nextLine();
            String line = iFH.nextLine();
            HouseHeading = HouseHeading + line;

            for (int i = 0; i < 11-line.length(); i++) 
            {
                HouseHeading += " "; 
            }
            
            Random rand = new Random();
            
            if(rand.nextInt(3) == 0)
            {
                /* then add a CandyHouse to the ArrayList of Houses */
                Houses.add(new CandyHouse(line, CandyList)); 
            }   
            /* else add a ToothbrushHouse to the ArrayList of Houses */
            else
            {
                Houses.add(new ToothbrushHouse(line, CandyList));
            }
    
        }
        
        iFH.close();
        /* concatenate HouseHeading and 2 newlines */
        HouseHeading += "\n\n";
        return HouseHeading;
    }
    
    public static void CreateTOTs(String TOTfilename,  ArrayList <TrickOrTreater> TOT, ArrayList <House> Houses)
    {
        File FH = new File (TOTfilename);
        Scanner iFH = null;
        
        try
        {
            iFH = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.printf("\n%s file name does not exist...exiting\n", TOTfilename);
            System.exit(0);
        }
        
        String element = " ";
        while(iFH.hasNextLine())
        {
           element = iFH.nextLine();
           /* add TrickOrTreater to ArrayList */
           TOT.add(new TrickOrTreater(element, Houses));
        }
        
        iFH.close();
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        String candyFile = "";
        String houseFile = "";
        String totFile = "";
       
        if (args.length != 3)
        {
            System.out.println("\n\nMissing command line parameters...exiting");
            System.exit(0);
        }
        else
        {
            candyFile = args[0].substring(args[0].indexOf("=") + 1);
            houseFile = args[1].substring(args[1].indexOf("=") + 1);
            totFile = args[2].substring(args[2].indexOf("=") + 1);
        }
       
        //rest of main 
        //Scanner in = new Scanner(System.in);
        
        HashMap <String, Integer> CandyList = new HashMap<>();
        ArrayList <House> Houses = new ArrayList<>();
        ArrayList<TrickOrTreater> TOT = new ArrayList<>();
        
        CreateCandyList(candyFile, CandyList);
        String HouseHeading = CreateHouses(houseFile, Houses, CandyList);
        CreateTOTs(totFile, TOT, Houses);
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        for(TrickOrTreater it : TOT)
        {
            executorService.execute(it); // where it is iterator over the ArrayList }
        }
        
        TextAreaFrame TAF = new TextAreaFrame();
        TAF.setVisible(true);
            
        while (TrickOrTreater.ImDone != TOT.size())
        {
            String ScreenOutput = String.format("%s", HouseHeading);
            
            for(TrickOrTreater it : TOT) 
            {
                ScreenOutput += String.format("%s%s\n", it.getPath(), it.getName()); 
            }
            TAF.textArea.setText(ScreenOutput);
        }
        
        executorService.shutdown();
        String BucketContents = "Candy!!\n\n";
        for(TrickOrTreater it : TOT)
        {
            //BucketContents.concat(it);
            BucketContents += it.printBucket();
            //return printBucket();
        }
        TAF.textArea.setText(BucketContents);
        
     
    }    
    
}
