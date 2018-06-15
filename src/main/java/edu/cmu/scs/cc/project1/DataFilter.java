package edu.cmu.scs.cc.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * <p>Implement data filter with test-driven development.</p>
 *
 * <p>
 * A valid line in the pageview files has 4 space-separated fields: <br>
 *
 * {@code domain_code page_title count_views total_response_size} <br>
 *
 * Transform and filter the dataset by the following rules:
 * <ul>
 * <li>Exclude lines that don't have four columns</li>
 * <li>Exclude lines if the the domain code is not exactly "en" or "en.m" (case
 * sensitive)
 * "en" indicates the article is an English desktop page, and "en.m" is for
 * English mobile page</li>
 * <li>The title might be percent-encoded by Wikipedia, use the provided
 * {@link PercentDecoder#decode(String)} method to decode the title for each
 * record
 * e.g. "Special%3ASearch" will be decoded into "Special:Search"</li>
 * <li>Exclude lines that the title starts with any prefix defined in
 * {@link #PREFIX_BLACKLIST} (case insensitive)</li>
 * <li>Exclude lines that the title ends with any suffix defined in
 * {@link #SUFFIX_BLACKLIST} (case insensitive)</li>
 * <li>Exclude lines if the title starts with any lowercase English
 * character</li>
 * <li>Exclude lines if the title is exactly any of the special pages defined in
 * the provided list {@link #SPECIAL_PAGES} (case sensitive)</li>
 * </ul>
 * </p>
 *
 * We provide you with the starting template, as well as the code of constants,
 * Standard I/O, summing the desktop and mobile site pageviews, and sorting the
 * output.
 *
 * <p>Your task is to implement the methods marked with "To be implemented".</p>
 *
 * <p>Execute the command {@code mvn test} to run the unit tests in
 * DataFilterTest</p>
 */
public final class DataFilter {

    /**
     * Column Name.
     */
    private static final int DOMAIN = 0, TITLE = 1, ACCESS = 2;
    /**
     * Column length.
     */
    private static final int CLEAN_DATA_LENGTH = 4;

    /**
     * Prefix blacklist.
     */
    private static final String[] PREFIX_BLACKLIST = {"media:",
            "special:",
            "talk:",
            "user:",
            "user_talk:",
            "wikipedia:",
            "wikipedia_talk:",
            "file:",
            "file_talk:",
            "mediawiki:",
            "mediawiki_talk:",
            "template:",
            "template_talk:",
            "help:",
            "help_talk:",
            "category:",
            "category_talk:",
            "portal:",
            "portal_talk:",
            "book:",
            "book_talk:",
            "draft:",
            "draft_talk:",
            "education_program:",
            "education_program_talk:",
            "timedtext:",
            "timedtext_talk:",
            "module:",
            "module_talk:",
            "gadget:",
            "gadget_talk:",
            "gadget_definition:",
            "gadget_definition_talk:"};
    /**
     * Suffix blacklist.
     */
    private static final String[] SUFFIX_BLACKLIST = {".png", ".gif",
            ".jpg", ".jpeg",
            ".tiff", ".tif",
            ".xcf", ".mid",
            ".ogg", ".ogv",
            ".svg", ".djvu",
            ".oga", ".flac",
            ".opus", ".wav",
            ".webm", ".ico", ".txt",
            "_(disambiguation)"};

    /**
     * Special pages.
     */
    private static final String[] SPECIAL_PAGES = {
            "Main_Page", "404.php", "-"};


    /**
     * Utility classes should not have a public or default constructor.
     */
    private DataFilter() {

    }

    /**
     * Main entry.
     *
     * The main method reads from {@link System#in}
     * and writes to `output`.
     *
     * I/O must be encoding-aware instead of relying on the system default
     * encoding. {@link OutputStreamWriter(OutputStream)} and
     * {@link InputStreamReader(InputStream)()} below are encoding-naive.
     * You need to replace them with their encoding-aware constructor
     * counterparts.
     *
     * @param args command-line args
     * @throws IOException if IO error occurs.
     */
    public static void main(final String[] args) throws IOException {
        // modify and make the try-with-resources statement encoding aware
        try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream("output")), true);
             BufferedReader br = new BufferedReader(
                     new InputStreamReader(System.in))) {
            // do not change the code below
            TreeMap<String, Integer> pageviewMap = new TreeMap<>();
            String page;
            while ((page = br.readLine()) != null) {
                String[] columns = getColumns(page);
                if (checkAllRules(columns)) {
                    try {
                        pageviewMap.put(columns[TITLE],
                                pageviewMap.getOrDefault(columns[TITLE], 0)
                                        + Integer.parseInt(columns[ACCESS]));
                    } catch (NumberFormatException e) {
                        //ignore it
                    }
                }
            }
            LinkedList<Entry<String, Integer>> linkedList = sortRecords(pageviewMap);
            for (Entry<String, Integer> entry : linkedList) {
                printWriter.print(entry.getKey() + "\t" + entry.getValue() + "\n");
            }
        }
    }

    /**
     * Sort the map in descending numerical order of the values,
     * break ties by the ascending lexicographical order of keys,
     * and return a list of sorted entries.
     *
     * @param records to sort
     * @return sorted entry list.
     */
    private static LinkedList<Entry<String, Integer>> sortRecords(
            final TreeMap<String, Integer> records) {
        LinkedList<Entry<String, Integer>> results =
                new LinkedList<>(records.entrySet());
        Collections.sort(results, (e1, e2) -> {
            int cmp = -e1.getValue().compareTo(e2.getValue());
            if (cmp != 0) {
                return cmp;
            } else {
                return e1.getKey().compareTo(e2.getKey());
            }
        });
        return results;
    }

    /**
     * Perform percent-decoding and split the record into columns,
     * separated by single or consecutive whitespaces.
     *
     * We pre-implemented this method for you to help you follow and learn how
     * to
     * perform test-driven development.
     *
     * @param record the pageview record
     * @return columns as a String array
     */
    static String[] getColumns(final String record) {
        return PercentDecoder.decode(record).split("\\s+");
    }

    /**
     * Check if the record passes all the rules.
     *
     * You do not need to modify this method. Instead, you
     * should divide and conquer the complicated filtering task by implementing:
     * {@link #checkDataLength(String[])}
     * {@link #checkDomain(String[])}
     * {@link #checkSpecialPage(String[])}
     * {@link #checkPrefix(String[])}
     * {@link #checkSuffix(String[])}
     * {@link #checkFirstLetter(String[])}
     *
     * @param columns record as columns
     * @return true if the record passes all the rules
     */
    static boolean checkAllRules(final String[] columns) {
        return checkDataLength(columns)
                && checkDomain(columns)
                && checkSpecialPage(columns)
                && checkPrefix(columns)
                && checkSuffix(columns)
                && checkFirstLetter(columns);
    }

    /**
     * Check if column length == 4.
     *
     * @param columns record as columns
     * @return true if length == 4
     */
    static boolean checkDataLength(final String[] columns) {
        throw new RuntimeException("To be implemented");
    }

    /**
     * Check if the domain code is en or en.m (case sensitive).
     *
     * @param columns record as columns
     * @return true if the domain code is en or en.m
     */
    static boolean checkDomain(final String[] columns) {
        throw new RuntimeException("To be implemented");
    }

    /**
     * Check if the title is any special page, case sensitive.
     *
     * @param columns record as columns
     * @return false if it is a special page
     */
    static boolean checkSpecialPage(final String[] columns) {
        throw new RuntimeException("To be implemented");
    }

    /**
     * Check if the title starts with any blacklisted prefix, case insensitive.
     *
     * Any occurrences of {@code %3a} should have been decoded into {@code :}.
     *
     * @param columns record as columns
     * @return false if the title starts with any blacklisted prefix
     */
    static boolean checkPrefix(final String[] columns) {
        throw new RuntimeException("To be implemented");
    }

    /**
     * Check if the title ends with any blacklisted suffix, case insensitive.
     *
     * @param columns record as columns
     * @return false if the title ends with any blacklisted suffix
     */
    static boolean checkSuffix(final String[] columns) {
        throw new RuntimeException("To be implemented");
    }

    /**
     * Check if the first letter of the title is an English lowercase letter.
     *
     * Many other Unicode characters are lowercase too.
     * Only [a-z] should count.
     *
     * Hint: Be careful and read the Javadoc if you want to use
     * {@link Character#isLowerCase(char)}.
     *
     * @param columns record as columns
     * @return false if the title starts with [a-z]
     */
    static boolean checkFirstLetter(final String[] columns) {
        throw new RuntimeException("To be implemented");
    }
}
