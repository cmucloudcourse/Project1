package edu.cmu.scs.cc.project1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Usage:
 * mvn test
 *
 * You should pass all the provided test cases before you make any submission.
 *
 * Feel free to add more test cases.
 */
class DataFilterTest {

    @Test
    void getColumns() {
        assertTrue(Arrays.equals(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0"),
                new String[]{"en", "Carnegie_Mellon_University", "34", "0"}));
        assertTrue(Arrays.equals(
                DataFilter.getColumns("en User%3AK6ka 34 0"),
                new String[]{"en", "User:K6ka", "34", "0"}));
    }

    @Test
    void checkDataLength() {
        assertTrue(DataFilter.checkDataLength(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkDataLength(
                DataFilter.getColumns("en 34 0")));
        assertFalse(DataFilter.checkDataLength(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 34 0")));
        assertFalse(DataFilter.checkDataLength(
                DataFilter.getColumns("en Carnegie_Mellon_University%2034 34 0")));
    }

    @Test
    void checkDomain() {
        assertTrue(DataFilter.checkDomain(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertTrue(DataFilter.checkDomain(
                DataFilter.getColumns("en.m Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkDomain(
                DataFilter.getColumns("fr Carnegie_Mellon_University 34 0")));
    }

    @Test
    void checkSpecialPage() {
        assertTrue(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en Main_Page 34 0")));
        assertFalse(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en - 34 0")));
        assertFalse(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en %2D 34 0")));
        assertTrue(DataFilter.checkSpecialPage(DataFilter.getColumns("en Main_Pager 481324 0")));
        assertTrue(DataFilter.checkSpecialPage(DataFilter.getColumns("en.m -da 382871 0")));
    }

    @Test
    void checkPrefix() {
        assertTrue(DataFilter.checkPrefix(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkPrefix(
                DataFilter.getColumns("en User:K6ka 34 0")));
        assertFalse(DataFilter.checkPrefix(
                DataFilter.getColumns("en User%3AK6ka 34 0")));
        assertFalse(DataFilter.checkPrefix(
                DataFilter.getColumns("en User%3aK6ka 34 0")));
    }

    @Test
    void checkSuffix() {
        assertTrue(DataFilter.checkSuffix(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkSuffix(
                DataFilter.getColumns("en picture.png 34 0")));
        assertFalse(DataFilter.checkSuffix(
                DataFilter.getColumns("en Media%3AK6kafavicon.ico 34 0")));
        assertFalse(DataFilter.checkSuffix(
                DataFilter.getColumns("en.m robots.txt 34 0")));
    }

    @Test
    void checkFirstLetter() {
        assertTrue(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en picture 34 0")));
        assertFalse(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en iPad 34 0")));
        assertTrue(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en.m 0robots.txt 34 0")));
        assertTrue(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en.m ∂abc 34 0")));
        assertTrue(DataFilter.checkFirstLetter(
                DataFilter.getColumns("en.m -BZaWKWmbnHbCeqtnVtUciLFfFem 484906 0")));

    }

    @Test
    void checkAllRules() {
        assertTrue(DataFilter.checkAllRules(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkAllRules(
                DataFilter.getColumns("en picture 34 0")));
        assertFalse(DataFilter.checkAllRules(
                DataFilter.getColumns("en iPad 34 0")));
        assertFalse(DataFilter.checkAllRules(
                DataFilter.getColumns("en.m 0robots.txt 34 0")));
        assertTrue(DataFilter.checkAllRules(
                DataFilter.getColumns("en.m ∂abc 34 0")));
    }

    @Test
    void testPercentDecoding(){
        System.out.println(PercentDecoder.decode( "XpMbjcqZMIejBmvwhLKkfxSBIiwZQx%2D%60%3E").toLowerCase());

    }
}