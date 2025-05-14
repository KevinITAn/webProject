package org.example.webproject.controller;

import org.example.webproject.model.Card;
import org.example.webproject.model.CardCondition;
import org.example.webproject.model.CardType;
import org.example.webproject.model.User;
import org.example.webproject.service.CardService;
import org.example.webproject.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    private final CardService cardService;

    private final UserService userService;

    public MainController(CardService cardService, UserService userService) {
        this.cardService = cardService;
        this.userService = userService;
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
        if (card == null)
            return "redirect:/notFoundCard";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();

        model.addAttribute("card", card);
        model.addAttribute("currentUsername", currentUsername);//passo attuale chi è loggato
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

        String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!card.getAuthor().getUsername().equals(loggedUsername)) {
            return "redirect:/notFoundCard";//TODO vedere se posso modificare in modo bello
        }

        model.addAttribute("card", card);
        model.addAttribute("actionUrl", "/card/" + id + "/edit");
        return "cardEdit";
    }


    @GetMapping("/card/{id}/delete")
    public String deleteCard(@PathVariable int id, Model model) {
        Card card= cardService.getCardById(id);
        String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!card.getAuthor().getUsername().equals(loggedUsername)) {
            return "redirect:/notFoundCard";//TODO vedere se posso modificare in modo bello
        }

        cardService.deleteCard(card);
        return "redirect:/";
    }

    @GetMapping("/notFoundCard")
    public String notFoundCard() {
        return "notFoundCard";
    }

    @PostMapping("/card/{id}/edit")
    public String updateCard(@PathVariable int id,
                             @RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                             @RequestParam("cardCondition") CardCondition cardCondition,
                             @RequestParam("type") CardType type,
                             @RequestParam(value = "img", required = false) MultipartFile imgFile) throws IOException {
        //controllo se la carta esiste
        if(cardService.getCardById(id)==null)
            return "redirect: notFoundCard";

        //aggiorna i dati
        cardService.updateCard(id,name,description,date,cardCondition,type,imgFile);
        return "redirect:/";
    }

    @PostMapping("/card/new")
    public String createCard(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                             @RequestParam("cardCondition") CardCondition cardCondition,
                             @RequestParam("type") CardType type,
                             @RequestParam("img") MultipartFile imgFile,
                             @AuthenticationPrincipal UserDetails userDetails) throws IOException {

        Card card = new Card();
        card.setName(name);
        card.setDescription(description);
        card.setDate(date);
        card.setCardCondition(cardCondition);
        card.setType(type);
        card.setImg(imgFile.getBytes());

        // Imposta l'autore
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        card.setAuthor(user);

        cardService.saveCard(card);
        return "redirect:/";
    }

}
