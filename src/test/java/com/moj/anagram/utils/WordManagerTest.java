package com.moj.anagram.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WordManagerTest {

  @Mock
  private SortUtils sortUtils;

  @Mock
  private URL wordListUrl;

  @InjectMocks
  private WordManager wordManager;

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testGetWordsBySortedCharacterUniqueness_validUrl_wordsReturned() throws IOException {

    when(sortUtils.sortWord(Mockito.anyString())).thenCallRealMethod();
    URLConnection connection = mock(URLConnection.class);
    when(wordListUrl.openConnection()).thenReturn(connection);

    InputStream is =
        this.getClass().getClassLoader().getResourceAsStream("Test3WordsSameChars.txt");
    when(connection.getInputStream()).thenReturn(is);

    Map<String, Set<String>> wordsBySortedCharactersMap =
        wordManager.getWordsBySortedCharacterUniqueness();

    assertEquals(1, wordsBySortedCharactersMap.size());
    assertEquals(3, wordsBySortedCharactersMap.get("ADEH").size());
  }

  @Test(expected = RuntimeException.class)
  public void testGetWordsBySortedCharacterUniqueness_invalidUrl_ExceptionThrown()
      throws IOException {

    when(wordListUrl.openConnection()).thenThrow(IOException.class);
    wordManager.getWordsBySortedCharacterUniqueness();

  }

}
