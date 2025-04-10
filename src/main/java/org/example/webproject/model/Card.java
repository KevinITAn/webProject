package org.example.webproject.model;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class Card {
    private String name;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String author;

    private CardCondition condition;

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", author='" + author + '\'' +
                ", condition=" + condition +
                '}';
    }
}
