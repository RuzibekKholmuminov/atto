package org.example.controllerUser;

import org.example.dto.Profile;
import org.example.dto.Transaction;
import org.example.enums.TransactionType;
import org.example.service.TransactionService;
import org.example.util.ScannerUtil;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UserController {
    TransactionService transactionService;

    public UserController() {
        this.transactionService = new TransactionService();

    }

    public void start(Profile profile) {
        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1 -> card(profile);
                case 2 -> reFill(profile);
                case 3 -> transaction(profile);
                case 4 -> make_payment(profile);
                case 0 -> game = false;
                default -> System.out.println("Mazgi nima bu");
            }
        }

    }

    private void make_payment(Profile profile) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter card number: ");
        String number = scanner.next();


        System.out.print("Enter terminal code: ");
        String terminal_c = scanner.next();

        Transaction transaction = new Transaction(number, 1400L, terminal_c, TransactionType.Payment, LocalDateTime.now());

        transactionService.make_payment(transaction,profile.getPhone());

    }

    private void transaction(Profile profile) {
        transactionService.get_profile_transaction_list(profile.getPhone());
    }

    private void reFill(Profile profile) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter card number: ");
        String number = scanner.next();

        System.out.println("Enter amount: ");
        Long amount = scanner.nextLong();

        transactionService.profile_reFill(profile.getPhone(), number, amount);


    }

    private void card(Profile profile) {
        UserCardController userCardController = new UserCardController();
        userCardController.start(profile);
    }


    public void menu() {
        System.out.println("***** PROFILE MENU *****");
        System.out.println("1.Card");
        System.out.println("2.ReFill");
        System.out.println("3.Transaction");
        System.out.println("4.Make Payment");
        System.out.println("0.Back to Menu");
    }


}
