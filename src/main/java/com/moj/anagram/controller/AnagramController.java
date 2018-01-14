package com.moj.anagram.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moj.anagram.controller.response.AnagramResponse;
import com.moj.anagram.exceptions.WordsNotFoundException;
import com.moj.anagram.solvers.AnagramSolver;

@RestController
public class AnagramController {

  private AnagramSolver anagramSolver;

  public AnagramController(final AnagramSolver anagramSolver) {
    this.anagramSolver = anagramSolver;
  }

  @GetMapping(value = "/anagram/{word}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(code = HttpStatus.OK)
  public AnagramResponse findAnagrams(@PathVariable("word") List<String> words) {
    final AnagramResponse response = new AnagramResponse();
    words.forEach(word -> response.addAnagram(word, anagramSolver.solve(word)));
    return response;
  }


  @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE,
      reason = "Failed to retrieve list of words")
  @ExceptionHandler(WordsNotFoundException.class)
  public void handleError() {

  }

}
