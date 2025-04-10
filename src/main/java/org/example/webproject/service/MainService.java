package org.example.webproject.service;

import org.example.webproject.model.Card;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    public String getHomePage(List<Card> cardListIn) {
        System.out.println(cardListIn);
        Resource resource = new ClassPathResource("/templates/index.html");
        String file = null;

        try {
            file = resource.getContentAsString(Charset.defaultCharset());
        } catch (IOException e) {
            System.err.println("Errore nel leggere il file HTML: " + e.getMessage());
            return "<h1>Errore nel caricamento della pagina</h1>";
        }

        // Se la lista è vuota, non inseriamo nessuna card
        String cardsHtml = cardListIn.isEmpty()
                ? ""
                : cardListIn.stream()
                .map(this::generateCardHtml)
                .collect(Collectors.joining());

        file = file.replace("$$cards", cardsHtml);
        System.out.println(file);
        return file;
    }

    public String generateCardHtml(Card card) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Genera una rappresentazione HTML della card usando un div
        return "<div class='card'>" +
                "<h3>" + card.getName() + "</h3>" +  // Titolo della card
                "<p><strong>Author:</strong> " + card.getAuthor() + "</p>" +  // Autore
                "<p><strong>Description:</strong> " + card.getDescription() + "</p>" +  // Descrizione
                "<p><strong>Date:</strong> " + sdf.format(card.getDate()) + "</p>" +  // Data della card
                "<p><strong>Condition:</strong> " + card.getCondition() + "</p>" +  // Condizione della card
                "</div>";
    }

    public String getFormNewCard() {
        Resource resource = new ClassPathResource("templates/cardSaleForm.html");
        String file = null;

        try {
            file=resource.getContentAsString(Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }



}
