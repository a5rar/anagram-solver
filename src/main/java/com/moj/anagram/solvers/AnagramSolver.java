package com.moj.anagram.solvers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.moj.anagram.utils.SortUtils;
import com.moj.anagram.utils.WordManager;

@Component
public class AnagramSolver {

  private WordManager wordManager;
  private SortUtils sortUtils;

  public AnagramSolver(final WordManager wordManager, final SortUtils sortUtils) {
    this.wordManager = wordManager;
    this.sortUtils = sortUtils;
  }

  public Set<String> solve(final String word) {
    Set<String> anagrams = wordManager.getWordsBySortedCharacterUniqueness()
        .getOrDefault(sortUtils.sortWord(word), new HashSet<String>());
    anagrams.remove(word);
    return anagrams;
  }
}
