package com.ouchin.ITLens.entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private int selectionCount = 0;

    @Transient
    private double percentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;


    public void incrementSelectionCount() {
        this.selectionCount++;
    }

    public void updatePercentage(int totalSelections) {
        if (totalSelections > 0) {
            this.percentage = (double) selectionCount / totalSelections * 100;
        } else {
            this.percentage = 0;
        }
    }
}


// To DO : create Answer endpointe
