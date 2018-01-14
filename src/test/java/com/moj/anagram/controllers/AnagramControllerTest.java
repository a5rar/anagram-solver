package com.moj.anagram.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.moj.anagram.exceptions.WordsNotFoundException;
import com.moj.anagram.solvers.AnagramSolver;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class AnagramControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AnagramSolver anagramsolver;

  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testAnagramController_validString_returnsValidResponse() throws Exception {
    Set<String> anagrams = new HashSet<>();
    anagrams.add("liarb");
    when(anagramsolver.solve("blair")).thenReturn(anagrams);
    this.mockMvc.perform(get("/anagram/blair")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("liar")))

        .andDo(document("anagram",
            PayloadDocumentation.responseFields(
                PayloadDocumentation.fieldWithPath("anagramsByWord")
                    .description("List of anagrams for a word"),
                PayloadDocumentation.fieldWithPath("anagramsByWord.blair")
                    .description("word used to map anagrams"))));

  }

  @Test
  public void testAnagramController_multipleValidString_returnsValidResponse() throws Exception {
    Set<String> anagrams = new HashSet<>();
    anagrams.add("liarb");
    when(anagramsolver.solve("blair")).thenReturn(anagrams);
    this.mockMvc.perform(get("/anagram/blair,liar")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("liar")))

        .andDo(document("multiple-anagram",
            PayloadDocumentation.responseFields(
                PayloadDocumentation.fieldWithPath("anagramsByWord")
                    .description("List of anagrams for a word"),
                PayloadDocumentation.fieldWithPath("anagramsByWord.blair")
                    .description("word usd to map anagrams"),
                PayloadDocumentation.fieldWithPath("anagramsByWord.liar")
                    .description("word used to map anagrams"))));

  }

  @Test
  public void testAnagramController_validString_BadRequest() throws Exception {
    Set<String> anagrams = new HashSet<>();
    anagrams.add("liarb");
    when(anagramsolver.solve("blair")).thenThrow(WordsNotFoundException.class);
    this.mockMvc.perform(get("/anagram/blair")).andDo(print())
        .andExpect(status().is5xxServerError());

  }
}


