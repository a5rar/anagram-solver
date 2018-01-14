package com.moj.anagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.moj.anagram.utils.WordManager;

@EnableCaching
@SpringBootApplication
public class AnagramSolverApplication {

  @Autowired
  private WordManager wordManager;

  public static void main(String[] args) {
    SpringApplication.run(AnagramSolverApplication.class, args);
  }

  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEvent() {
    wordManager.getWordsBySortedCharacterUniqueness();
  }
}
