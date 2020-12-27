package com.card.consumer.Service;


import com.card.consumer.Entity.UserEntity;
import com.card.consumer.Entity.CardEntity;
import com.card.consumer.Repository.UserRepo;
import com.card.consumer.Repository.cardRepo;
import com.card.consumer.types.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo repo;
    private cardRepo cardRepo;
    private ModelMapper modelMapper;
    private UserDAO userDao;
    private UserDTO userDTO;
   private RestTemplate restTemplate;
   private CardDAO cardDAO;






    @Autowired
    public UserService(UserRepo repo, cardRepo cardRepo, ModelMapper modelMapper) {
        this.repo = repo;
        this.cardRepo = cardRepo;
        this.modelMapper = modelMapper;
        this.userDao = new UserDAO();
        this.userDTO = new UserDTO();
        this.restTemplate = new RestTemplate();

    }

    //Add an User
    public UserDAO postUser(UserDTO newUser)
    {
        UserEntity user = modelMapper.map(newUser, UserEntity.class);
        repo.save(user);

        Optional<UserEntity> getUser = repo.findById(user.getId());
        UserEntity foundUser = getUser.get();

        if(getUser.isPresent())
        {
            userDao.setId(foundUser.getId());
            userDao.setStatus("New Account Created");

        }

        return userDao;
    }

    //Get All Users
    public List<UserEntity> findAllUsers()
    {
        LocalDate currentDate = LocalDate.now() ;
        Month month = currentDate.getMonth();
        String stringMonth = month.toString();
        System.out.println("Month: " + month);
        System.out.println("Month: " + stringMonth);
        return repo.findAll();
    }

    //Add Card
    public CardDAO addCard(CardDTO newCard)
    {
        CardEntity card = modelMapper.map(newCard, CardEntity.class);
        cardRepo.save(card);

        Optional<CardEntity> getCard = cardRepo.findById(card.getId());
        CardEntity foundUser = getCard.get();

        if(getCard.isPresent())
        {
            cardDAO.setId(foundUser.getId());
            cardDAO.setStatus("New card Added");

        }

        return cardDAO;

    }

    public ResponseEntity<cardPOJO> getOffer()
    {
        String url = "http://localhost:9090/1";
//        ResponseEntity<cardPOJO> response = restTemplate.exchange(URL, HttpMethod.GET,null ,cardPOJO.class);
//        System.out.println("Response" + response);
//        return (List<cardPOJO>) response;
        return restTemplate.getForEntity(url, cardPOJO.class);


    }

    public String getOfferByLocation()
    {
        String url = "http://localhost:9090/Discover/Everyday/December/Walmart";
        LocalDate currentDate = LocalDate.now() ;
        Month month = currentDate.getMonth();
        String stringMonth = month.toString();
        System.out.println("Month: " + month);
        System.out.println("Month: " + stringMonth);
        ResponseEntity<cardPOJO> response =  restTemplate.getForEntity(url, cardPOJO.class);
        System.out.println("Response: " +response);
        cardPOJO getCard = response.getBody();
        return getCard.getCardOffer();


    }

}
