package com.example.wordle;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * The configuration of WordsDictionary.<br/>
 * The dictionary is populated based on the classpath file
 * /static/words.properties.
 */
@Configuration
public class WordsDictionaryConfig {

    /**
     * Define the file that contains the dictionary.
     */
    private static final String dictionaryFilePath = "/static/words.properties";

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public WordsDictionary wordsDictionary() {
        return new WordsDictionary(dictionaryFilePath);
    }
}
