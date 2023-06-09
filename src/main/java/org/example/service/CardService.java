package org.example.service;

import org.example.dto.Card;
import org.example.dto.Transaction;
import org.example.enums.GeneralStatus;
import org.example.repository.CardRepository;
import org.example.repository.TransactionRepository;

import java.util.List;

public class CardService {
    private CardRepository cardRepository;

    public CardService() {
        this.cardRepository = new CardRepository();
    }

    public void addCard_toDb(Card card) {
        List<Card> card1 =  cardRepository.searchCardByNumber(card.getNumber());
        for (Card card2: card1) {
            if (card2 != null) {
                System.out.println("Bunday karta mavjud");
                return;
            }
        }

        int i = cardRepository.addCardToDb(card);

    }


    public void get_card_list() {
        List<Card> card_list = cardRepository.get_card_list();

        System.out.println("                                ** Card List **");
        for (Card card : card_list) {
            System.out.println(card);

        }
        System.out.println();

    }

    public void update(Card card) {

        int n = cardRepository.updateCardFromDb(card);
        if (n != 0) {
            System.out.println("Updated");
        } else {
            System.out.println("Failed");
        }
    }

    public void changeStatus(String number) {
        String status = "";
        int i = 0;
        List<Card> card =  cardRepository.searchCardByNumber(number);
        for (Card card1 : card) {
            if (card1 == null) {
                System.out.println("Bunday card yoq");
                return;
            }
            if (card1.getStatus().equals(GeneralStatus.ACTIVE.toString())) {
                status = GeneralStatus.BLOCK.name();
                i = cardRepository.changeACTIVEStatus(number, status);
            } else if (card1.getStatus().equals(GeneralStatus.BLOCK.toString())) {
                status = GeneralStatus.ACTIVE.name();
                i = cardRepository.changeBLOCKStatus(number, status);
            }
        }

        if (i != 0) {
            System.out.println("Changed to" + status);
        } else {
            System.out.println("Failed");
        }

    }

    public void reFill(Transaction transaction) {

        List<Card> card =  cardRepository.searchCardByNumber(transaction.getCard_number());
        for (Card card1 : card) {
            if (card1 == null) {
                System.out.println("The card is not exists");
                return;
            }
        }
        TransactionRepository transactionRepository = new TransactionRepository();
        transactionRepository.reFill(transaction);
    }

    public void deleteCard(String number) {
        int i = cardRepository.deleteCardFromDb(number);
        if (i != 0) {
            System.out.println("Deleted");
        } else {
            System.out.println("Failed");
        }

    }


    public void companyCard() {
        cardRepository.companyCard();
    }
}
