package edu.cmu.scs.cc.project1;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ParseDateValueTest {

    @Test
    void getTotalViews() {
        Map<String,Long> testMap = new HashMap<>();
        testMap.put("20161101",50l);
        testMap.put("20161102",40l);
        testMap.put("20161103",90l);

        assertEquals(ParseDateValue.getTotalViews(testMap),180);
    }

    @Test
    void areTitleViewsMoreThanCutoff() {
        Map<String,Long> testMapTrue = new HashMap<>();
        testMapTrue.put("20161101",50l);
        testMapTrue.put("20161102",40l);
        testMapTrue.put("20161103",100000l);

        Map<String,Long> testMapFalse = new HashMap<>();
        testMapFalse.put("20161101",50l);
        testMapFalse.put("20161102",40l);


        assertTrue(ParseDateValue.areTitleViewsMoreThanCutoff(testMapTrue));
        assertFalse(ParseDateValue.areTitleViewsMoreThanCutoff(testMapFalse));

    }

    @Test
    void parseData() throws UnsupportedEncodingException {
        Map<String,Long> testMap = new TreeMap<>();
        testMap.put("20161101",50l);
        testMap.put("20161102",40l);
        testMap.put("20161103",100000l);

        testMap.put("20161128",100l);

        ParseDateValue.parseData("Test-Title", (TreeMap<String, Long>) testMap);
    }
}