package com.example.wordle;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Defines the API for wordle. <br/>
 * Use the propose method to get a list of proposed words based on criteria.
 * 
 */
@RestController
public class WordleController {

	/**
	 * Initialize the wordsDictionary.
	 */
	@Autowired
	WordsDictionary wordsDictionary;

	/**
	 * Display the dictionary size.
	 * 
	 * @return String
	 */
	@GetMapping("/")
	public String index() {
		return String.format("Greetings from Wordle! The dictionary contains %d words!",
				wordsDictionary.getAllWords().size());
	}

	/**
	 * Search in the WordsDictionary words that match the WordleInput and return the
	 * list of proposed words.
	 * 
	 * @param input
	 * @return A list of proposed words
	 * @throws IOException
	 */
	@PostMapping("/propose")
	public List<String> propose(@RequestBody WordleInput input) throws IOException {
		Solver solver = new Solver(wordsDictionary);
		List<String> proposedWords = solver.findWords(input);
		proposedWords.sort(new DuplicateLetterComparator());
		return proposedWords;
	}

}
