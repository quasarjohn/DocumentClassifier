package com.berstek.models;


import javax.persistence.Column;
import javax.persistence.Entity;

//@Entity
public class Lexeme {

//    @Column
    private String parent_document;
//    @Column
    private String word;
//    @Column
    private Integer frequency;

    public String getParent_document() {
        return parent_document;
    }

    public void setParent_document(String parent_document) {
        this.parent_document = parent_document;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}
