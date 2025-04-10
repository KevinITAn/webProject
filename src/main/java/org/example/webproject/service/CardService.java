package org.example.webproject.service;

import lombok.Getter;
import org.example.webproject.model.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    List<Card> cardList=new ArrayList<>();

    public List<Card> getCardList(){
        return cardList;
    }

    public void saveCard(Card cardIn){
        cardList.add(cardIn);
    }

}
