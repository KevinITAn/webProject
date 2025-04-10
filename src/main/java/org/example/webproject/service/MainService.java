package org.example.webproject.service;

import org.example.webproject.model.Card;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    public String getHomePage(List<Card> cardListIn){
        Resource resource = new ClassPathResource("templates/index.html");
        String file=null;
        try{
            file=resource.getContentAsString(Charset.defaultCharset());
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        //build list for view
        String cardLis = cardListIn.stream().map(card -> "<li>"+card.getName()+"</li>").collect(Collectors.joining());
        //addList for view
        file = file.replace("$$cards", cardLis);
        return file;
    }

}
