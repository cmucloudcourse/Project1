package edu.cmu.scs.cc.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ReducerTest {

    @Test
    void mapDateAndValue() {
        String testString1 = "20161101|::|20";
        String testString2 = "20161101|::|30";
        String testString3 = "20161103|::|40";
        String testString4 = "20161102|::|40";
        String testString5 = "20161103|::|20";
        String testString6 = "20161104|::|10";

        Reducer.mapDateAndValue(testString1);
        Reducer.mapDateAndValue(testString2);
        Reducer.mapDateAndValue(testString3);
        Reducer.mapDateAndValue(testString4);
        Reducer.mapDateAndValue(testString5);
        Reducer.mapDateAndValue(testString6);

        System.out.println(Reducer.dateValueMap);

        assertEquals((long)Reducer.dateValueMap.get("20161101"),50);
        assertEquals((long)Reducer.dateValueMap.get("20161102"),40);
        assertEquals((long)Reducer.dateValueMap.get("20161103"),60);
        assertEquals((long)Reducer.dateValueMap.get("20161104"),10);
    }
}