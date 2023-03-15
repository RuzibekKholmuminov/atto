package org.example.service;

import org.example.controller.AuthController;
import org.example.dto.Card;
import org.example.dto.Transaction;
import org.example.enums.TransactionType;
import org.example.repository.CardRepository;
import org.example.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionService {

    TransactionRepository transactionRepository;

    public TransactionService() {
        this.transactionRepository = new TransactionRepository();
    }

    public void get_transaction_list() {

        List<Transaction> transaction_list_from_db = transactionRepository.get_transaction_list_from_db();

        for (Transaction transaction : transaction_list_from_db) {
            System.out.println(transaction);
        }

    }

    public void profile_reFill(String phone, String number, Long amount) {

        CardRepository cardRepository = new CardRepository();
        List<Card> card = cardRepository.searchCardByNumber(number);
        for (Card card1 : card) {
            if (card1 == null) {
                System.out.println("Bunday card yoq");
                return;
            }
            if (card1.getPhone() == null) {
                System.out.println("This card is not yours");
                return;
            }

            if (!card1.getPhone().equals(phone)) {
                System.out.println("This card is not yours");
                return;
            }
        }

        int i = transactionRepository.profile_refill(number, amount);

        if (i == 0) {
            System.out.println("Failed");
            return;
        }

        Transaction transaction = new Transaction(number, amount, null, TransactionType.ReFill, LocalDateTime.now());

        transactionRepository.addTransaction(transaction);

    }

    public void get_profile_transaction_list(String phone) {
        List<Transaction> profile_transaction_list_fromDb = transactionRepository.get_profile_transaction_list_fromDb(phone);
        for (Transaction transaction : profile_transaction_list_fromDb) {
            System.out.println(transaction);
        }

    }

    public void make_payment(Transaction transaction, String phone) {

        CardRepository cardRepository = new CardRepository();
        List<Card> card = cardRepository.searchCardByNumber(transaction.getCard_number());
        for (Card card1 : card) {
            if (card1 == null) {
                System.out.println("The card is not available");
                return;
            }
            if (card1.getPhone()==null||!card1.getPhone().equals(phone)) {
                System.out.println("The card is not yours");
                return;
            }
        }





        int i = transactionRepository.make_payment(transaction,phone);


    }


}
