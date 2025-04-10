package org.example.webproject.controller;

import org.example.webproject.service.CardService;
import org.example.webproject.service.MainService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    CardService cardService=new CardService();
    MainService mainService=new MainService();

    @GetMapping("/")
    public ResponseEntity<String> indexReturn(){
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)//poi usiamo un service cosi da dividere la logica
                .body(mainService.getHomePage(cardService.getCardList()));
    }

}
