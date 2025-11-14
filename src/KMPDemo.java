import java.util.ArrayList;
import java.util.List;
/**
 * KMP string matching implementation.
 * This class demonstrates how the Knuth–Morris–Pratt algorithm
 * finds all occurrences of a pattern inside a given text.
 */
public class KMPDemo {
    /**
     * Builds the LPS (Longest Prefix-Suffix) array for the pattern.
     *
     * lps[i] stores the length of the longest proper prefix of pattern[0..i]
     * which is also a suffix of pattern[0..i].
     *
     * This preprocessing allows the algorithm to avoid re-checking characters
     * after mismatches during the search phase.
     */
    private static int[] buildLps(String pattern) {
        int m =pattern.length();
        int[] lps =new int[m];
        int len =0;     // length of the current longest prefix-suffix
        int i =1;       // lps[0] is always 0, so we start from index 1

        while (i <m) {
            if (pattern.charAt(i) ==pattern.charAt(len)) {
                // Characters match → extend the current prefix-suffix
                len++;
                lps[i] =len;
                i++;
            } else {
                // Mismatch case
                if (len !=0) {
                    // Go to the previous longest prefix-suffix
                    len =lps[len - 1];
                } else {
                    // No prefix-suffix found → set 0
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * Performs the KMP string search.
     *
     * @param text the text in which we search
     * @param pattern the pattern to find
     * @return a list of starting indices where the pattern occurs in the text
     *
     * The search runs in linear time, because the LPS array tells the algorithm
     * how much to shift the pattern without re-checking characters.
     */
    public static List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> result =new ArrayList<>();
        if (pattern.isEmpty()) return result;

        int n =text.length();
        int m =pattern.length();
        int[] lps =buildLps(pattern);

        int i =0;  // index in text
        int j =0;  // index in pattern

        while (i < n) {
            if (text.charAt(i) ==pattern.charAt(j)) {
                // Characters match → move both pointers
                i++;
                j++;

                // Entire pattern matched
                if (j ==m) {
                    result.add(i -j);   // store the match position
                    j = lps[j -1];      // continue searching for next matches
                }

            } else {
                // Mismatch
                if (j !=0) {
                    // Use LPS to avoid unnecessary comparisons
                    j = lps[j -1];
                } else {
                    // No partial match → move forward in text
                    i++;
                }
            }
        }
        return result;
    }

    /**
     * Main method used for testing the KMP algorithm with:
     * - a short string
     * - a medium-length string
     * - a long string (to observe performance)
     */
    public static void main(String[] args) {

        // === Test 1: Short string ===
        String text1 ="ababcabcabababd";
        String pattern1 ="ababd";
        System.out.println("=== Test 1: Short string ===");
        System.out.println("Matches: " + kmpSearch(text1, pattern1));
        System.out.println();

        // === Test 2: Medium-length string ===
        String text2 ="the quick brown fox jumps over the lazy dog";
        String pattern2 ="the";
        System.out.println("=== Test 2: Medium string ===");
        System.out.println("Matches: " + kmpSearch(text2, pattern2));
        System.out.println();

        // === Test 3: Long string ===
        // We generate a long string by repeating "abcde" many times
        StringBuilder sb =new StringBuilder();
        for (int i =0; i <20000; i++) {
            sb.append("abcde");
        }
        String text3 =sb.toString();
        String pattern3 ="cdeab";

        long start =System.nanoTime();
        List<Integer> matches =kmpSearch(text3, pattern3);
        long end =System.nanoTime();

        System.out.println("=== Test 3: Long string ===");
        System.out.println("Text length: " + text3.length());
        System.out.println("Matches found: " + matches.size());
        System.out.println("Time (ms): " + (end - start) / 1_000_000.0);
    }
}