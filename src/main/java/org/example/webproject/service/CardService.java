package org.example.webproject.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.example.webproject.model.Card;
import org.example.webproject.model.CardCondition;
import org.example.webproject.model.CardType;
import org.example.webproject.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@Service
public class CardService {

    @Autowired
    private CardRepository repoCard;

    public Card getCardById(int id){
        return repoCard.findById(id).orElse(null);
    }

    public void updateCard(int id,String name, String description, Date date, CardCondition condition, CardType type, MultipartFile file) throws IOException {
        Card cardRsc=getCardById(id);
        if(cardRsc==null)
            return;
        //set new val
        cardRsc.setName(name);
        cardRsc.setDescription(description);
        cardRsc.setCardCondition(condition);
        cardRsc.setType(type);
        cardRsc.setDate(date);
        if(!file.isEmpty())
            cardRsc.setImg(file.getBytes());
        //persisto le info
        repoCard.save(cardRsc);
    }

    public List<Card> search(String field, String query) {
        if (field == null || query == null) {
            return Collections.emptyList();
        }

        switch (field.toLowerCase()) { // Case-insensitive per sicurezza
            case "name":
                return repoCard.findByNameContainingIgnoreCase(query);
            case "description":
                return repoCard.findByDescriptionContainingIgnoreCase(query);
            case "author":
                return repoCard.findByAuthorUsernameContainingIgnoreCase(query);
            case "type":
                    return repoCard.findByType(CardType.valueOf(query.toUpperCase()));
            case "cardcondition":
                    return repoCard.findByCardCondition(CardCondition.valueOf(query.toUpperCase()));
            default:
                return Collections.emptyList();
        }
    }

    public List<Card> getCardList(){
        return repoCard.findAll();
    }

    public void saveCard(Card cardIn){
        repoCard.save(cardIn);
    }

    public void deleteCard(Card card){
        repoCard.delete(card);
    }


}
