package com.berstek.services;

import com.berstek.models.Lexeme;
import com.berstek.repository.FreqTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FreqTableService {

    @Autowired
    FreqTableRepository repository;

    public void saveFreqTable(String category, String word, Integer count) {
        repository.saveFreqTable(category, word, count);
    }

    public Iterable<String> getCategories() {
        return repository.getCategories();
    }

    public Integer getFrequency(String category, String word) {
        return repository.getFrequency(category, word);
    }

    public Integer getVocabularyCount() {
        return repository.getVocabularyCount();
    }
}
