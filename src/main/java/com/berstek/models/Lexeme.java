package com.berstek.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Lexeme.ProjectId.class)
@Table(name = "frequency_table")
public class Lexeme{

    @Column
    @Id
    private String category;

    @Column
    @Id
    private String word;

    @Column
    private Integer frequency;

    class ProjectId implements Serializable {

        public ProjectId(String category, String word) {
            this.category = category;
            this.word = word;
        }

        String category;
        String word;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
