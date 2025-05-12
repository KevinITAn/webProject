package org.example.webproject.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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
    //@Lob dice a JPA che è un Large Object (BLOB o CLOB).
    //columnDefinition = "LONGBLOB" forza Hibernate a generare il tipo corretto nel database.
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] img;


    @Enumerated(EnumType.STRING)
    private CardCondition cardCondition;//codition non posso usarlo perchè da errore

    @Enumerated(EnumType.STRING)
    private CardType type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    //solo per visualizzazione quindi non la salvo nel DB @Transient
    //non salvo manco con @Transient cosi non appesantisco nulla ma colcolato a runtime
    @Transient
    public String base64Img(){
        return Base64.getEncoder().encodeToString(img);
    }


}
