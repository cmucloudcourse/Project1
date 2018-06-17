package edu.cmu.scs.cc.project1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static edu.cmu.scs.cc.project1.DataFilter.checkAllRules;

public class Mapper {

    private static final int TITLE = 1;
    private static final int ACCESS = 2;
    private static final int DATE =1 ;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(
                     new InputStreamReader(new FileInputStream(System.getenv("mapreduce_map_input_file")), StandardCharsets.UTF_8))) {
            String page;
            while ((page = br.readLine()) != null) {
                String[] columns = DataFilter.getColumns(page);
                if (checkAllRules(columns)) {
                    try {
                        System.out.println(columns[TITLE]+"-"+getDate(System.getenv("mapreduce_map_input_file"))+"\t"+columns[ACCESS]);
                    } catch (NumberFormatException e) {
                        //ignore it
                    }
                }
            }

        }
    }

    private static String getDate(String fileName){
        String[] tokens = fileName.split("-");
        return tokens[DATE];
    }
}
