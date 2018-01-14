package com.moj.anagram.solvers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.moj.anagram.utils.SortUtils;
import com.moj.anagram.utils.WordManager;


@RunWith(MockitoJUnitRunner.class)
public class AnagramSolverTest {

  @Mock
  private WordManager wordManager;

  @Mock
  private SortUtils sortUtils;

  @InjectMocks
  private AnagramSolver anagramSolver;

  @Before
  public void setUp() throws Exception {

    when(sortUtils.sortWord(Mockito.anyString())).thenCallRealMethod();
  }

  @Test
  public void testSolve_validWordMatch_ReturnsSet() {

    Set<String> anagrams = new HashSet<>();
    anagrams.add("HEAD");
    anagrams.add("ADHE");

    Map<String, Set<String>> wordsByCharUniqueness = new HashMap<>();
    wordsByCharUniqueness.put("ADEH", anagrams);
    when(wordManager.getWordsBySortedCharacterUniqueness()).thenReturn(wordsByCharUniqueness);
    Set<String> anagramResponse = anagramSolver.solve("DHEA");

    assertEquals(anagrams.size(), anagramResponse.size());
  }


  @Test
  public void testSolve_NoWordMatch_ReturnsemptySet() {
    when(wordManager.getWordsBySortedCharacterUniqueness()).thenReturn(new HashMap<>());
    Set<String> anagramResponse = anagramSolver.solve("DHEA");

    assertEquals(0, anagramResponse.size());
  }

  @Test
  public void testSolve_validWordMatch_RemovedEqualWord() {

    Set<String> anagrams = new HashSet<>();
    anagrams.add("HEAD");
    anagrams.add("ADHE");
    anagrams.add("ADEH");

    Map<String, Set<String>> wordsByCharUniqueness = new HashMap<>();
    wordsByCharUniqueness.put("ADEH", anagrams);
    when(wordManager.getWordsBySortedCharacterUniqueness()).thenReturn(wordsByCharUniqueness);
    Set<String> anagramResponse = anagramSolver.solve("ADEH");

    assertEquals(anagrams.size(), anagramResponse.size());
  }

}
