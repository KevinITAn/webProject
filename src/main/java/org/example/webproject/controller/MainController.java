package org.example.webproject.controller;

import org.example.webproject.model.Card;
import org.example.webproject.model.CardCondition;
import org.example.webproject.model.CardType;
import org.example.webproject.model.User;
import org.example.webproject.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    private final CardService cardService;

    public MainController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Card> cardList=cardService.getCardList();
        for(Card src : cardList)
            System.out.println(src.getName());
        model.addAttribute("cards",cardList);
        return "index";
    }
    @GetMapping("card/{id}")
    public String getCard(@PathVariable("id") int id, Model model){
        Card card = cardService.getCardById(id);
        if (card == null) {
            return "redirect:/notFoundCard";
        }
        model.addAttribute("card", card);
        return "cardDetail";
    }

    @GetMapping("card/new")
    public String newCard(Model model) {
        model.addAttribute("card", new Card());
        model.addAttribute("actionUrl", "/card/new");
        return "cardSaleForm";
    }

    @GetMapping("/card/{id}/edit")
    public String editCard(@PathVariable int id, Model model) {
        Card card = cardService.getCardById(id);
        if (card == null)
            return "redirect:/notFoundCard";
        model.addAttribute("card", card);
        model.addAttribute("actionUrl", "/card/" + id + "/edit");
        return "cardEdit";
    }

    @GetMapping("/card/{id}/delete")
    public String deleteCard(@PathVariable int id, Model model) {
        cardService.deleteCard(id);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        return "registerForm";
    }


    @PostMapping("/card/{id}/edit")
    public String updateCard(@PathVariable int id,
                             @RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("author") String author,
                             @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                             @RequestParam("cardCondition") CardCondition cardCondition,
                             @RequestParam("type") CardType type,
                             @RequestParam(value = "img", required = false) MultipartFile imgFile) throws IOException {
        //aggiorna i dati
        cardService.updateCard(id,name,description,author,date,cardCondition,type,imgFile);
        return "redirect:/";
    }


    @PostMapping("/card/new")
    public String createCard(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("author") String author,
                             @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                             @RequestParam("cardCondition") CardCondition cardCondition,
                             @RequestParam("type") CardType type,
                             @RequestParam("img") MultipartFile imgFile) throws IOException {

        Card card = new Card();
        card.setName(name);
        card.setDescription(description);
        card.setAuthor(author);
        card.setDate(date);
        card.setCardCondition(cardCondition);
        card.setImg(imgFile.getBytes()); // converti l'immagine in byte[]

        // salva la carta
        cardService.saveCard(card);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute User user) {
        return "redirect:/";
    }

}
