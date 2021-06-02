package com.github.wildcards4j;

import junit.framework.TestCase;

import static com.github.wildcards4j.WildcardsUtils.equalsByWildcards;
import static com.github.wildcards4j.WildcardsSearchAlgorithm.DYNAMIC;
import static com.github.wildcards4j.WildcardsSearchAlgorithm.RECURSIVE;

public class WildcardsUtilsTest extends TestCase {
    public void testEqualsByWildcards1() {
        assertEquals(equalsByWildcards("abc", "*abc"),
                     equalsByWildcards("abc", "*abc", DYNAMIC));
        assertFalse(equalsByWildcards(null, "*abc"));
        assertFalse(equalsByWildcards("abc", null));
        assertFalse(equalsByWildcards("abc", "*abc", null));
    }

    public void testEqualsByWildcardsDynamic() {
        // Positive Tests
        assertTrue(equalsByWildcards("", "*", DYNAMIC));
        assertTrue(equalsByWildcards(" ", "?", DYNAMIC));
        assertTrue(equalsByWildcards("a", "*", DYNAMIC));
        assertTrue(equalsByWildcards("a", "*?", DYNAMIC));
        assertTrue(equalsByWildcards("a", "?*" ));
        assertTrue(equalsByWildcards("a", "*?*", DYNAMIC));
        assertTrue(equalsByWildcards("ab", "*", DYNAMIC));
        assertTrue(equalsByWildcards("ab","**", DYNAMIC));
        assertTrue(equalsByWildcards("ab", "***", DYNAMIC));
        assertTrue(equalsByWildcards("ab", "*b", DYNAMIC));
        assertTrue(equalsByWildcards("ab", "a*", DYNAMIC));
        assertTrue(equalsByWildcards("a", "?", DYNAMIC));
        assertTrue(equalsByWildcards("abc", "*?", DYNAMIC));
        assertTrue(equalsByWildcards("abc", "*??", DYNAMIC));
        assertTrue(equalsByWildcards("abc", "??*", DYNAMIC));
        assertTrue(equalsByWildcards("abc", "?*?", DYNAMIC));
        assertTrue(equalsByWildcards("abc", "???", DYNAMIC));
        assertTrue(equalsByWildcards("abc", "?*", DYNAMIC));
        assertTrue(equalsByWildcards("abc", "*abc", DYNAMIC));
        assertTrue(equalsByWildcards("abc", "*abc*", DYNAMIC));
        assertTrue(equalsByWildcards("abc", "*a*b*c*", DYNAMIC));
        assertTrue(equalsByWildcards("aXXXbc", "*a*bc*", DYNAMIC));

        // Negative Tests
        assertFalse(equalsByWildcards("", "*a", DYNAMIC));
        assertFalse(equalsByWildcards("", "a*", DYNAMIC));
        assertFalse(equalsByWildcards("", "?", DYNAMIC));
        assertFalse(equalsByWildcards("a", "*b*", DYNAMIC));
        assertFalse(equalsByWildcards("ab", "b*a", DYNAMIC));
        assertFalse(equalsByWildcards("ab", "a?b", DYNAMIC));
        assertFalse(equalsByWildcards("ab", "abc", DYNAMIC));
        assertFalse(equalsByWildcards("cab", "ab", DYNAMIC));
        assertFalse(equalsByWildcards("abc", "ab", DYNAMIC));
        assertFalse(equalsByWildcards("AB", "ab", DYNAMIC));
        assertFalse(equalsByWildcards("ab", "AB", DYNAMIC));
        assertFalse(equalsByWildcards("ab", "cab", DYNAMIC));
        assertFalse(equalsByWildcards("a", "??", DYNAMIC));
        assertFalse(equalsByWildcards("", "*?", DYNAMIC));
        assertFalse(equalsByWildcards("", "?*", DYNAMIC));
        assertFalse(equalsByWildcards("abc", "????", DYNAMIC));
        assertFalse(equalsByWildcards("a", "??*", DYNAMIC));
        assertFalse(equalsByWildcards("abX", "*abc", DYNAMIC));
        assertFalse(equalsByWildcards("Xbc", "*abc*", DYNAMIC));
        assertFalse(equalsByWildcards("ac", "*a*bc*", DYNAMIC));
    }

    public void testEqualsByWildcardsRecursive() {
        // Positive Tests
        assertTrue(equalsByWildcards("", "*", RECURSIVE));
        assertTrue(equalsByWildcards(" ", "?", RECURSIVE));
        assertTrue(equalsByWildcards("a", "*", RECURSIVE));
        assertTrue(equalsByWildcards("a", "*?", RECURSIVE));
        assertTrue(equalsByWildcards("a", "?*" ));
        assertTrue(equalsByWildcards("a", "*?*", RECURSIVE));
        assertTrue(equalsByWildcards("ab", "*", RECURSIVE));
        assertTrue(equalsByWildcards("ab","**", RECURSIVE));
        assertTrue(equalsByWildcards("ab", "***", RECURSIVE));
        assertTrue(equalsByWildcards("ab", "*b", RECURSIVE));
        assertTrue(equalsByWildcards("ab", "a*", RECURSIVE));
        assertTrue(equalsByWildcards("a", "?", RECURSIVE));
        assertTrue(equalsByWildcards("abc", "*?", RECURSIVE));
        assertTrue(equalsByWildcards("abc", "*??", RECURSIVE));
        assertTrue(equalsByWildcards("abc", "??*", RECURSIVE));
        assertTrue(equalsByWildcards("abc", "?*?", RECURSIVE));
        assertTrue(equalsByWildcards("abc", "???", RECURSIVE));
        assertTrue(equalsByWildcards("abc", "?*", RECURSIVE));
        assertTrue(equalsByWildcards("abc", "*abc", RECURSIVE));
        assertTrue(equalsByWildcards("abc", "*abc*", RECURSIVE));
        assertTrue(equalsByWildcards("abc", "*a*b*c*", RECURSIVE));
        assertTrue(equalsByWildcards("aXXXbc", "*a*bc*", RECURSIVE));

        // Negative Tests
        assertFalse(equalsByWildcards("", "*a", RECURSIVE));
        assertFalse(equalsByWildcards("", "a*", RECURSIVE));
        assertFalse(equalsByWildcards("", "?", RECURSIVE));
        assertFalse(equalsByWildcards("a", "*b*", RECURSIVE));
        assertFalse(equalsByWildcards("ab", "b*a", RECURSIVE));
        assertFalse(equalsByWildcards("ab", "a?b", RECURSIVE));
        assertFalse(equalsByWildcards("ab", "abc", RECURSIVE));
        assertFalse(equalsByWildcards("cab", "ab", RECURSIVE));
        assertFalse(equalsByWildcards("abc", "ab", RECURSIVE));
        assertFalse(equalsByWildcards("AB", "ab", RECURSIVE));
        assertFalse(equalsByWildcards("ab", "AB", RECURSIVE));
        assertFalse(equalsByWildcards("ab", "cab", RECURSIVE));
        assertFalse(equalsByWildcards("a", "??", RECURSIVE));
        assertFalse(equalsByWildcards("", "*?", RECURSIVE));
        assertFalse(equalsByWildcards("", "?*", RECURSIVE));
        assertFalse(equalsByWildcards("abc", "????", RECURSIVE));
        assertFalse(equalsByWildcards("a", "??*", RECURSIVE));
        assertFalse(equalsByWildcards("abX", "*abc", RECURSIVE));
        assertFalse(equalsByWildcards("Xbc", "*abc*", RECURSIVE));
        assertFalse(equalsByWildcards("ac", "*a*bc*", RECURSIVE));
    }
}