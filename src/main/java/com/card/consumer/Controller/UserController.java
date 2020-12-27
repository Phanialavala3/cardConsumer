package com.card.consumer.Controller;

import com.card.consumer.Entity.UserEntity;
import com.card.consumer.Service.UserService;
import com.card.consumer.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/")
    public UserDAO addUser(@RequestBody UserDTO dto) {
        return service.postUser(dto);
    }

    @GetMapping("/")
    public List<UserEntity> getAllUsers() {
        return service.findAllUsers();
    }

//    @GetMapping("/{cardProvider}/{cardType}/{location}")
//    public String findOfferByCard(@PathVariable String cardProvider, @PathVariable String cardType, @PathVariable String location)
//    {
//        re turn service.getOffer(cardProvider, cardType,location);
//    }

    @GetMapping("/all")
    public ResponseEntity<cardPOJO> allCards()
    {
        System.out.println("Came to controller");
        return service.getOffer();
    }

    @GetMapping("/card")
    public String offer()
    {
        System.out.println("Came to controller");
        return service.getOfferByLocation();
    }

    @PostMapping("/newCard")
    public CardDAO addNewCardForUser(@RequestBody CardDTO cardDTO)
    {
        return service.addCard(cardDTO);
    }
}
