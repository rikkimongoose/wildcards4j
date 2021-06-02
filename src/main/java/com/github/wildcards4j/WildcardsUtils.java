package com.github.wildcards4j;
import java.util.Arrays;

import static java.util.Objects.isNull;

/**
 * The wildcards support methods. The wildcard pattern can include the characters ‘?’ and ‘*’
 * ‘?’ – matches any single character.
 * ‘*’ – Matches any sequence of characters (including the empty sequence).
 *
 * "b?t" matches "bat", "bot", but not "beet".
 * "b*t" matches "bat", "bot", "beet", "bt", "best", "be a carrot".
 */
public class WildcardsUtils {

    /**
     * Asterisk character
     */
    private static final Character asteriskChar = '*';

    /**
     * Question character
     */
    private static final Character questionChar = '?';

    /**
     * Default search algorithm
     */
    private static final WildcardsSearchAlgorithm WILDCARDS_SEARCH_ALGORITHM_DEFAULT = WildcardsSearchAlgorithm.DYNAMIC;

    /**
     * Compare string with wildcards mask, using default algorithm.
     * @param str string to check
     * @param pattern wildcard mask
     * @return <code>true</code> if string matches wildcard pattern, otherwise <code>false</code>
     */
    public static boolean equalsByWildcards(String str, String pattern) {
        return equalsByWildcards(str, pattern, WILDCARDS_SEARCH_ALGORITHM_DEFAULT);
    }

    /**
     * Compare string with wildcards mask, using specified algorithm.
     * @param str string to check
     * @param pattern wildcard mask
     * @param wildcardsSearchAlgorithm compare algorithm
     * @return <code>true</code> if string matches wildcard pattern, otherwise <code>false</code>.
     *
     */
    public static boolean equalsByWildcards(String str, String pattern, WildcardsSearchAlgorithm wildcardsSearchAlgorithm) {
        if(isNull(str) || isNull(pattern) || isNull(wildcardsSearchAlgorithm)) {
            return false;
        }

        if (str.length() == 0) {
            return pattern.length() == 0 || hasOnlyAsterisks(pattern);
        }

        switch (wildcardsSearchAlgorithm){
            case DYNAMIC: return equalsByWildcardsDymanic(str, pattern);
            case RECURSIVE: return equalsByWildcardsRecursive(str, 0, str.length(), pattern, 0, pattern.length());
        }
        return equalsByWildcardsDymanic(str, pattern);
    }

    /**
     * Checks, do string contains only asterisk symbols
     * @param str string to check
     * @return <code>true</code>, if string contains only '*' symbols, otherwise <code>false</code>
     */
    private static boolean hasOnlyAsterisks(String str) {
        for(int i=0;i<str.length();i++){
            if(!asteriskChar.equals(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compare with wildcard algorithm algorithm based on
     * @see <a href="https://www.geeksforgeeks.org/wildcard-pattern-matching/">Dynamic Progamming implementation</a>
     *
     * If m = length of string, n = length of wildcard filter
     * Time complexity: O(m x n)
     * Auxillary space: O(m x n)
     * @param str string to check
     * @param pattern wildcard mask
     * @return <code>true</code> if string matches wildcard pattern, otherwise <code>false</code>
     */
    private static boolean equalsByWildcardsDymanic(String str, String pattern) {
        int strLength = str.length();
        int wildcardsFilterLength = pattern.length();

        // lookup table for storing results of subproblems
        boolean[][] lookup = new boolean[strLength + 1][wildcardsFilterLength + 1];

        // initialise lookup table to false
        for (int i = 0; i < strLength + 1; i++)
            Arrays.fill(lookup[i], false);

        // empty pattern can match with empty string
        lookup[0][0] = true;

        // Only '*' can match with empty string
        for (int j = 1; j <= wildcardsFilterLength; j++)
            if (pattern.charAt(j - 1) == '*')
                lookup[0][j] = lookup[0][j - 1];

        // fill the table in bottom-up fashion
        for (int i = 1; i <= strLength; i++)
        {
            for (int j = 1; j <= wildcardsFilterLength; j++)
            {
                // Two cases if we see a '*'
                // a) We ignore '*'' character and move to next  character in the pattern,
                //     i.e., '*' indicates an empty sequence.
                // b) '*' character matches with its character in input
                if (pattern.charAt(j - 1) == '*')
                    lookup[i][j] = lookup[i][j - 1] || lookup[i - 1][j];

                // Current characters are considered as matching in two cases
                // (a) current character of pattern is '?'
                // (b) characters actually match
                else if (questionChar.equals(pattern.charAt(j - 1))
                        || str.charAt(i - 1) == pattern.charAt(j - 1))
                    lookup[i][j] = lookup[i - 1][j - 1];
                // If characters don't match
                else
                    lookup[i][j] = false;
            }
        }
        return lookup[strLength][wildcardsFilterLength];
    }

    /**
     * Compare with wildcard algorithm based on
     * @see <a href="https://stackoverflow.com/questions/25671967/whats-time-complexity-of-this-algorithm-for-wildcard-matching/">Recursive implementation</a>
     * If m = length of string, n = length of wildcard filter
     * Time complexity: O(2^min(m, n))
     * Auxillary space: O(2^min(m, n))
     * @param str string to check
     * @param pattern wildcard mask
     * @return <code>true</code> if string matches wildcard pattern, otherwise <code>false</code>
     */
    private static boolean equalsByWildcardsRecursive(String str, int sIndex, int sLen,
                                                      String pattern, int pIndex, int pLen) {
        // Base case.
        if (sIndex >= sLen && pIndex >= pLen) return true;
        else if (sIndex >= sLen) {
            // Check whether the remaining part of p all "*".
            while (pIndex < pLen) {
                if (!asteriskChar.equals(pattern.charAt(pIndex))) return false;
                pIndex++;
            }
            return true;

        } else if (pIndex >= pLen) {
            return false;
        }

        char sc = str.charAt(sIndex);
        char pc = pattern.charAt(pIndex);

        if (questionChar.equals(pc) || pc == sc) {
            return equalsByWildcardsRecursive(str, sIndex + 1, sLen, pattern, pIndex + 1, pLen);
        } else if (asteriskChar.equals(pc)) {
            return equalsByWildcardsRecursive(str, sIndex, sLen, pattern, pIndex + 1, pLen) ||
                    equalsByWildcardsRecursive(str, sIndex + 1, sLen, pattern, pIndex, pLen);

        } else return false;
    }
}
