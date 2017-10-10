package com.berstek.repository;

import com.berstek.models.Lexeme;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FreqTableRepository extends CrudRepository<Lexeme, String> {

    @Modifying
    @Transactional
    @Query(value = "insert into frequency_table(category, word, frequency) " +
            "values(?1, ?2, ?3)", nativeQuery = true)
    void saveFreqTable(String category, String word, Integer frequency);

    @Query(value = "select distinct category from frequency_table", nativeQuery = true)
    Iterable<String> getCategories();

    @Query(value = "select frequency from frequency_table where category = ?1 and word = ?2", nativeQuery = true)
    Integer getFrequency(String category, String word);

    @Query(value = "select count(distinct(word)) from frequency_table", nativeQuery = true)
    Integer getVocabularyCount();
}
