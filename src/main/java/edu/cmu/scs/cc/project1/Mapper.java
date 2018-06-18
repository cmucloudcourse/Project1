package edu.cmu.scs.cc.project1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static edu.cmu.scs.cc.project1.DataFilter.checkAllRules;

public class Mapper {

    private static final int TITLE = 1;
    private static final int ACCESS = 2;
    private static final int DATE =1 ;

    public static void main(String[] args) throws IOException {
        try (   Scanner in = new Scanner(
                new BufferedInputStream(System.in), "UTF-8");
                BufferedReader br = new BufferedReader(
                     new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String page;
            while ((page = br.readLine()) != null) {
                String[] columns = DataFilter.getColumns(page);
                if (checkAllRules(columns)) {
                    try {
                        PrintWriter out = new PrintWriter(
                                new OutputStreamWriter(System.out, "UTF-8"), true);

                        out.print(PercentDecoder.decode(columns[TITLE])+"\t"+getDate(System.getenv("mapreduce_map_input_file"))+"|::|"+columns[ACCESS]+"\n");
                    } catch (NumberFormatException e) {
                        //ignore it
                    }
                }
            }

        }
    }

    static String getDate(String fileName){
        System.getenv("mapreduce_map_input_file");
        String[] tokens = fileName.split("-");
        return tokens[tokens.length-2];
    }
}
