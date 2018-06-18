package edu.cmu.scs.cc.project1;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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




    static void parseData(String title, TreeMap<String,Long> dateValueMap) throws UnsupportedEncodingException {
//        err.print("Class : ParseDataValue, method : parseData"+"\n");
        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(System.out, "UTF-8"), true);
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
            out.print(totalViews+"\t"+title);
            for (long l : intermediateOutput) {
                out.print("\t"+l);
            }
            out.print("\n");
            out.flush();
        }

//        err.print("title : "+title+" does NOT has more views than cutoff\n");



    }


}
