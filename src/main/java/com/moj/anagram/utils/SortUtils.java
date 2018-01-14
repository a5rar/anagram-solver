package com.moj.anagram.utils;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class SortUtils {

  public String sortWord(String word) {
    char[] ar = word.toCharArray();
    Arrays.sort(ar);
    return new String(ar);
  }

}
