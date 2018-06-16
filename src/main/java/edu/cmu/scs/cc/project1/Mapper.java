package edu.cmu.scs.cc.project1;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import static edu.cmu.scs.cc.project1.DataFilter.checkAllRules;

public class Mapper {

    private static final int DOMAIN = 0, TITLE = 1, ACCESS = 2, DATE =1 ;

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
