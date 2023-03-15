package org.example.controllerUser;

import org.example.dto.Profile;
import org.example.dto.Profile_Card;
import org.example.service.Profile_Card_Service;
import org.example.util.ScannerUtil;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UserCardController {
    Profile_Card_Service profile_card_service;

    public UserCardController() {
        this.profile_card_service = new Profile_Card_Service();

    }

    public void start(Profile profile) {
        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1 -> add_card(profile.getPhone());
                case 2 -> card_list(profile.getPhone());
                case 3 -> card_change_status(profile.getPhone());
                case 4 -> delete_card(profile.getPhone());
                case 0 -> game = false;
                default -> System.out.println("Mazgi nima bu");
            }
        }


    }

    private void delete_card(String phone) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter card number: ");
        String number = scanner.next();

        profile_card_service.delete_profile_card(phone,number);
    }

    private void card_change_status(String phone) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter card number: ");
        String number = scanner.next();

        profile_card_service.change_profile_card_status(phone,number);

    }

    private void card_list(String phone) {
        profile_card_service.get_profile_card_list(phone);
    }

    private void add_card(String phone) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter card number: ");
        String number = scanner.next();

        Profile_Card profile_card = new Profile_Card(phone, number, LocalDateTime.now());

        profile_card_service.addCard_to_User(profile_card);


    }


    private void menu() {
        System.out.println("**** CARD MENU ****");
        System.out.println("1.Add card");
        System.out.println("2.Card list");
        System.out.println("3.Card change status");
        System.out.println("4.Delete card");
        System.out.println("0.Back to PROFILE MENU");
    }
}
