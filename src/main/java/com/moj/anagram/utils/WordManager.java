package com.moj.anagram.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.moj.anagram.exceptions.WordsNotFoundException;

@CacheConfig(cacheNames = "words")
@Component
public class WordManager {

  private SortUtils sortUtils;

  private URL wordListUrl;

  public WordManager(SortUtils sortUtils, @Value("${moj.anagram.words.url}") URL wordListUrl) {
    this.sortUtils = sortUtils;
    this.wordListUrl = wordListUrl;
  }

  @Cacheable(unless = "#result != null and #result.size() == 0")
  public Map<String, Set<String>> getWordsBySortedCharacterUniqueness() {
    try (Stream<String> stream =
        new BufferedReader(new InputStreamReader(wordListUrl.openConnection().getInputStream()))
            .lines();) {

      return stream.collect(Collectors.groupingBy(sortUtils::sortWord,
          Collectors.mapping(Function.identity(), Collectors.toSet())));

    } catch (IOException e) {
      throw new WordsNotFoundException("Failed to load words");
    }
  }
}
