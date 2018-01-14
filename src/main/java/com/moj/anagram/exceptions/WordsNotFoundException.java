package com.moj.anagram.exceptions;

public class WordsNotFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 3683641551250352987L;

  public WordsNotFoundException(String message) {
    super(message);
  }

}
