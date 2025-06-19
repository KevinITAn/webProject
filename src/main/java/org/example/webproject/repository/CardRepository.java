package org.example.webproject.repository;

import org.example.webproject.model.Card;
import org.example.webproject.model.CardCondition;
import org.example.webproject.model.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    List<Card> findByNameContainingIgnoreCase(String name);
    List<Card> findByDescriptionContainingIgnoreCase(String description);
    List<Card> findByAuthorUsernameContainingIgnoreCase(String username);
    List<Card> findByType(CardType type);
    List<Card> findByCardCondition(CardCondition condition);



}
