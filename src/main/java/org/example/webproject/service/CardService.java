package org.example.webproject.service;

import lombok.Getter;
import org.example.webproject.model.Card;
import org.example.webproject.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class CardService {

    @Autowired
    private CardRepository repoCard;

    public Card getCardById(int id){
        return repoCard.getReferenceById(id);
    }

    public void updateCard(Card card){
        Card cardRsc=getCardById(card.getId());
        if(cardRsc==null)
            return;
        cardRsc.setName(card.getName());
        cardRsc.setDescription(card.getDescription());
        cardRsc.setAuthor(card.getAuthor());
        cardRsc.setCardCondition(card.getCardCondition());
        cardRsc.setType(card.getType());
        cardRsc.setDate(card.getDate());
    }

    public List<Card> getCardList(){
        return repoCard.findAll();
    }

    public void saveCard(Card cardIn){
        repoCard.save(cardIn);
    }

    public void deleteCard(int id){
        repoCard.deleteById(id);
    }

}
