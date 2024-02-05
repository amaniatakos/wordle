package com.example.wordle;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WordleControllerIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getBody()).isEqualTo("Greetings from Wordle! The dictionary contains 14855 words!");
    }

    @Test
    public void getPropose() throws Exception {
        var response = template.postForEntity("/propose", getSmileInput(), List.class);        
        var proposedWords = response.getBody();
        assertNotNull(proposedWords);
        assertThat(proposedWords.size() == 18).isTrue();
        assertThat(proposedWords.contains("SMILE")).isTrue();
        assertThat(proposedWords.contains("LOYAL")).isFalse();
    }

    WordleInput getSmileInput() {
        WordleInput input = new WordleInput();
        input.setExactMatch(List.of(Pair.of('m', 2)));
        input.setMisplaced(List.of(Pair.of('m', 1), Pair.of('m', 3), Pair.of('e', 2)));
        input.setNotExist(List.of('h', 'u', 'a', 'n', 't', 'r', 'o'));

        return input;
    }

}
