package edu.cmu.scs.cc.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Reducer {

    private static final int TITLE = 0;
    private static final int ACCESS = 1;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String input;
            String title = null;
            long access_total = 0;
            String currentTitle = null;
            //Read all sorted records one by one
            while ((input = br.readLine()) != null) {

                String[] columns = input.split("\t");
                title = columns[TITLE];
                try {
                    long access_current = Long.parseLong(columns[ACCESS]);

                    if (currentTitle != null && currentTitle.equals(title)) {
                        access_total = access_total + access_current;
                    } else {
                        if (currentTitle != null) //new word is encounterd{
                            System.out.println(currentTitle + "\t" + access_total);

                        currentTitle = title;
                        access_total = access_current;

                    }
                }catch (NumberFormatException nf){
                    //Do Nothing
                    continue;
                }
            }

            if (currentTitle != null && currentTitle.equals(title)){
                System.out.println(currentTitle + "\t" + access_total);
            }


        } catch (IOException e) {
            //doNothings
        }


    }
}
