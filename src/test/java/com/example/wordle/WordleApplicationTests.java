package com.example.wordle;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;


import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest
@AutoConfigureMockMvc
class WordleApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getHello() throws Exception {
		Matcher<String> greetingValidator = equalTo("Greetings from Wordle! The dictionary contains 14855 words!");
		assertNotNull(greetingValidator);

		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(greetingValidator));

	}

	@SuppressWarnings("null")
	@Test
	public void propose() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/propose")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(getSmileInput()))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsStringIgnoringCase("SMILE")));
	}

	WordleInput getSmileInput() {
		WordleInput input = new WordleInput();
		input.setExactMatch(List.of(Pair.of('s', 1), Pair.of('m', 2)));
		input.setMisplaced(List.of(Pair.of('e', 2)));
		input.setNotExist(List.of('h', 'u', 'a', 'n', 't', 'r', 'o'));

		return input;
	}

}
