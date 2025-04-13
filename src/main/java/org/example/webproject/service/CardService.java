package org.example.webproject.service;

import lombok.Getter;
import org.example.webproject.model.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class CardService {

    private final List<Card> cardList=new ArrayList<>();

    public int newId(){
         return cardList.size();
    }
    //problem many card
    public Card getCardById(int id){
        for(Card c:cardList)
            if(c.getId()==id)
                return c;
        return null;
    }

    public Card updateCard(Card card){
        Card cardRsc=getCardById(card.getId());
        if(cardRsc==null)
            return null;
        cardRsc.setName(card.getName());
        cardRsc.setDescription(card.getDescription());
        cardRsc.setAuthor(card.getAuthor());
        cardRsc.setCondition(card.getCondition());
        cardRsc.setType(card.getType());
        cardRsc.setDate(card.getDate());
        return cardRsc;
    }

    public void saveCard(Card cardIn){
        //devo prima mettere id
        cardIn.setId(newId());
        cardList.add(cardIn);
    }

    public void deleteCard(int id){
        Card cardRsc=getCardById(id);
        if(cardRsc==null)
            return;
        cardList.remove(cardRsc);
    }

}
