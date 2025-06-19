package org.example.webproject.service;

import org.example.webproject.model.Card;
import org.example.webproject.model.User;
import org.example.webproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

    public List<Card> getCart(User user){return user.getCart();}

    public long countUsers(){
        return userRepository.count();
    }

    public void addCart(User user,Card card){
        user.getCart().add(card);
        userRepository.save(user); //aggiorna
    }

    public void removeCart(User user,Card card){
        user.getCart().remove(card);
        userRepository.save(user);//aggiorna
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }


}
