package com.example.wordle;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordleController {

	private static final String dictionaryFilePath = "classpath:words.properties"; 
	
	WordsDictionary allCapitalWords = new WordsDictionary(dictionaryFilePath);
	
	Solver solver = new Solver(allCapitalWords);
	
	@GetMapping("/")
	public String index() {
		return String.format("Greetings from Wordle! The dictionary contains %d words!", allCapitalWords.getAllWords().size());
	}

	@PostMapping("/propose")
	@ResponseBody
	public List<String> propose(@RequestBody WordleInput input) throws IOException {
		List<String> proposedWords = solver.findWords(input);
		proposedWords.sort(new DuplicateLetterComparator());
		return proposedWords;
	}

}
