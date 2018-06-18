package edu.cmu.scs.cc.project1;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.System.*;

public class ParseDateValue {

    private static String title ;
    private static final int CUTOFF = 100000;
    private static long totalViews=0;
    static PrintWriter err = new PrintWriter( new OutputStreamWriter(System.err, StandardCharsets.UTF_8), true);

    static long getTotalViews(Map<String,Long> dateValueMap){
        totalViews = dateValueMap.values().stream().mapToLong(i -> i).sum();
        err.print(" Views : "+totalViews+"\n");
        return totalViews;

    }

    static boolean areTitleViewsMoreThanCutoff(Map<String,Long> dateValueMap){
        return getTotalViews(dateValueMap) > CUTOFF;
    }




    static void parseData(String title, TreeMap<String,Long> dateValueMap){
//        err.print("Class : ParseDataValue, method : parseData"+"\n");
        long [] intermediateOutput = new long[30];
        err.print("title : "+title);
        if(areTitleViewsMoreThanCutoff(dateValueMap)){
            err.print("title : "+title+" has more views than cutoff\n");
            for(int i = 0; i < 30 ; i++ ){
                if(dateValueMap.containsKey((Long.valueOf(dateValueMap.firstKey())+i)+"")){
                    intermediateOutput[i] = dateValueMap.get((Long.valueOf(dateValueMap.firstKey())+i)+"");
                }else{
                    intermediateOutput[i] = 0;
                }
            }
            System.out.print(totalViews+"\t"+title);
            for (long l : intermediateOutput) {
                System.out.print("\t"+l);
            }
            System.out.print("\n");
        }

//        err.print("title : "+title+" does NOT has more views than cutoff\n");



    }


}