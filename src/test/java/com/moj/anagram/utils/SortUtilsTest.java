package com.moj.anagram.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SortUtilsTest {



  @Before
  public void setUp() throws Exception {}

  @Test
  public void testSortWord_unsortedString_sortedString() {
    SortUtils sortUtils = new SortUtils();

    String word = "ihgfedcba";
    assertEquals("abcdefghi", sortUtils.sortWord(word));
  }

  @Test
  public void testSortWord_emptyString_emptyString() {
    SortUtils sortUtils = new SortUtils();
    String word = "";
    assertEquals("", sortUtils.sortWord(word));
  }
}
