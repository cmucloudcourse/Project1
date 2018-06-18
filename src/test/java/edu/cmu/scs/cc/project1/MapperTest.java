package edu.cmu.scs.cc.project1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    @Test
    void getDate() {
        String fileName = "pageviews-20161101-000000";
        String url = "s3://test-bucket-amazon.com/project1-bucket/inputbucket/pageviews-20161101-000000";
        System.out.println(Mapper.getDate(fileName));
        System.out.println(Mapper.getDate(url));
        assertEquals("20161101",Mapper.getDate(fileName));
        assertEquals("20161101",Mapper.getDate(url));

    }
}