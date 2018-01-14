package com.moj.anagram.controller.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnagramResponse {

  private Map<String, Set<String>> anagramsByWord = new HashMap<>();

  public Map<String, Set<String>> getAnagramsByWord() {
    return anagramsByWord;
  }

  public void setAnagramsByWord(Map<String, Set<String>> anagramsByWord) {
    this.anagramsByWord = anagramsByWord;
  }

  public void addAnagram(String word, Set<String> anagrams) {
    getAnagramsByWord().put(word, anagrams);
  }



}
