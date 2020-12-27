package com.card.consumer.types;

import lombok.Data;

@Data
public class cardPOJO {
    private Long cardId;
    private String cardProvider;
    private String cardType;
    private String offerMonth;
    private String location;
    private String cardOffer;
    private String status;
}
