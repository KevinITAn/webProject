package org.example.webproject.controller;

import org.example.webproject.model.Card;
import org.example.webproject.service.CardService;
import org.example.webproject.service.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    @GetMapping("/card/new")
    public ResponseEntity<String> newCard(){
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(mainService.getFormNewCard());
    }

    @PostMapping("/card/new")
    public String createCard(@ModelAttribute Card card){
        cardService.saveCard(card);
        return "redirect:/";
    }

//    @PostMapping("/api/card/new")
//    public ResponseEntity<Card> createCardAPI(@ModelAttribute Card card) {
//        cardService.saveCard(card);
//        return new ResponseEntity<>(card, HttpStatus.CREATED);
//    }

}
