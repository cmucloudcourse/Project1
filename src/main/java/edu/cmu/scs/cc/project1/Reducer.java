package edu.cmu.scs.cc.project1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Reducer {

    private static final String DELIMITER = "\\|::\\|";
    private static final int TITLE = 0;
    private static final int ACCESS = 1;
    static Map<String, Long> dateValueMap = new TreeMap<>();
    static PrintWriter err = new PrintWriter( new OutputStreamWriter(System.err, StandardCharsets.UTF_8), true);

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String input;
            String title = null;
            String currentTitle = null;
            err.println("Starting the reduce function " + "\n");
            //Read all sorted records one by one
            while ((input = br.readLine()) != null) {


                String[] columns = input.split("\t");
                title = columns[TITLE];
                try {
//                    err.println("Started reduction for title " +title+ "\n");

                    if (currentTitle != null && currentTitle.equals(title)) {
                        //Same Title -- Add the values to the map;
                        err.println("Adding title " +title+"to the map "+ "\n");
                        mapDateAndValue(columns[ACCESS]);
                    } else {
                        //new word is encounterd{
                        if (currentTitle != null) {
                            err.print("The current title is not the same as previous title.\n");
                            err.print("Current title : "+currentTitle+"Encountered title"+title+"\n");
                            ParseDateValue.parseData(currentTitle, (TreeMap<String, Long>) dateValueMap);
                            dateValueMap.clear();
                        }
                        currentTitle = title;
                        mapDateAndValue(columns[ACCESS]);

                    }
                }catch (NumberFormatException nf){
                    //Do Nothing
                    continue;
                }
            }

            if (currentTitle != null && currentTitle.equals(title)){
                ParseDateValue.parseData(currentTitle, (TreeMap<String, Long>) dateValueMap);
                dateValueMap.clear();
            }


        } catch (IOException e) {
            //doNothings
        }


    }

    static void mapDateAndValue(String str){
        int DATE = 0;
        int VAL = 1;
         String[] dateAndValue = str.split(DELIMITER);
         String date = dateAndValue[DATE];
         long val= getLongAccess(dateAndValue[VAL]);
        err.print("Class : ParseDataValue, method : parseData\n");
         //If the views for date exists add the number of views
         if(dateValueMap.containsKey(date)){
             long totalaccess = dateValueMap.get(date)+val;
             dateValueMap.put(date,totalaccess);
         }else {
             dateValueMap.put(date,val);
         }
    }

    private static long getLongAccess(String access){
        try{
            return Long.parseLong(access);
        }catch (NumberFormatException nfse){
            //do Nothing
            return 0;
        }
    }
}
