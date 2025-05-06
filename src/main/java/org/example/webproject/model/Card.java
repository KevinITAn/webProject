package org.example.webproject.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Card {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String description;
    private String author;

    @Enumerated(EnumType.STRING)
    private CardCondition cardCondition;//codition non posso usarlo perchè da errore

    @Enumerated(EnumType.STRING)
    private CardType type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
