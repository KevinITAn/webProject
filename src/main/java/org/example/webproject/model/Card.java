package org.example.webproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter @AllArgsConstructor @ToString
public class Card {
    private String name;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String author;

    private CardCondition condition;

}
