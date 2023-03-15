package org.example.service;

import org.example.dto.Card;
import org.example.dto.Profile_Card;
import org.example.enums.GeneralStatus;
import org.example.repository.CardRepository;
import org.example.repository.Profile_Card_Repository;

import java.util.List;

public class Profile_Card_Service {
    Profile_Card_Repository profile_card_repository;
    CardRepository cardRepository;


    public Profile_Card_Service() {
        this.profile_card_repository = new Profile_Card_Repository();
        cardRepository = new CardRepository();
    }

    public void addCard_to_User(Profile_Card profile_card) {
        CardRepository cardRepository = new CardRepository();
        Card card = (Card) cardRepository.searchCardByNumber(profile_card.getCard_number());
        if (card == null) {
            System.out.println("Bunday karta mavjud emas");
            return;
        }

        if (card.getPhone() != null) {
            System.out.println("Bu kartani ulay olmaysiz");
            return;
        }

        int i = profile_card_repository.addCard_to_user(profile_card);

        if (i != 0) {
            cardRepository.addPhone_to_Card(profile_card.getCard_number(), profile_card.getProfile_phone());
            System.out.println("Successfully");
        } else {
            System.out.println("Failed");

        }
    }


    public void get_profile_card_list(String phone) {

        List<Card> profile_card_list_fromDb = cardRepository.get_profile_card_list_fromDb(phone);

        for (Card card : profile_card_list_fromDb) {
            System.out.println(card.getNumber() + " # " + card.getExp_date() + " # " + card.getBalance());
        }
    }

    public void change_profile_card_status(String phone, String number) {
        List<Card> card =  cardRepository.searchCardByNumber(number);
        String status = GeneralStatus.BLOCK.name();

        for (Card card1 : card) {
            if (card1.getStatus().equals(GeneralStatus.ACTIVE.toString())) {
                status = GeneralStatus.BLOCK.toString();
                cardRepository.changeACTIVEStatus(phone, status);
            }else if (card1.getStatus().equals(GeneralStatus.BLOCK.toString())){
                status = GeneralStatus.ACTIVE.toString();
                cardRepository.changeBLOCKStatus(number,status);
            }
        }

        int i = cardRepository.change_profile_card_status_fromDB(phone, number, status);

        if (i != 0) {
            System.out.println("card status changed to " + status);
        } else {
            System.out.println("Failed");
        }
    }

    public void delete_profile_card(String phone, String number) {


        int i = profile_card_repository.delete_profile_card(phone, number);

        if (i != 0) {
            cardRepository.delete_phone_from_card(number);
            System.out.println("Deleted");
        } else {
            System.out.println("Failed");
        }


    }
}

