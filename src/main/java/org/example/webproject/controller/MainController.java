package org.example.webproject.controller;

import org.example.webproject.model.Card;
import org.example.webproject.model.User;
import org.example.webproject.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final CardService cardService;

    public MainController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("cards", cardService.getCardList());
        return "index";
    }
    @GetMapping("card/{id}")
    public String getCard(@PathVariable("id") int id, Model model){
        Card card=cardService.getCardById(id);
        if(card==null)
            return "redirect:/";
        model.addAttribute("card",card);
        return "cardDetail";
    }

    @GetMapping("/card/new")
    public String newCard(Model model) {
        return "cardSaleForm";
    }

    @GetMapping("/card/{id}/edit")
    public String editCard(@PathVariable int id, Model model) {
        Card card = cardService.getCardById(id);
        if(card==null)
            return "redirect:/";
        model.addAttribute("card", card);
        return "cardEdit";
    }

    @GetMapping("/card/{id}/delete")
    public String deleteCard(@PathVariable int id, Model model) {
        cardService.deleteCard(id);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @PostMapping("/card/{id}/edit")
    public String updateCard(@PathVariable int id, @ModelAttribute Card card) {
        cardService.updateCard(card);
        return "redirect:/";
    }

    @PostMapping("/card/new")
    public String createCard(@ModelAttribute Card card) {
        cardService.saveCard(card);
        System.out.println(cardService.getCardList());
        return "redirect:/";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute User user) {
        return "redirect:/";
    }

    @PostMapping("/api/card/new")
    public ResponseEntity<Card> createCardAPI(@RequestBody Card card) {
        cardService.saveCard(card);
        return new ResponseEntity<>(card, HttpStatus.CREATED);//201 HttpStatus.CREATED
    }

}
