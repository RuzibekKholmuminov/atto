package org.example.controller.controllerAdmin;

import org.example.db.DataBase;
import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.repository.ProfileRepository;
import org.example.service.CardService;
import org.example.service.TransactionService;
import org.example.util.ScannerUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminController {

    public void start() {
        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1 -> card();
                case 2 -> terminal();
                case 3 -> profile();
                case 4 -> transaction_list();
                case 5 -> company_card();
                case 6 -> deleteAdminByName();
                case 7 -> changePassword();
                case 0 -> game = false;
                default -> System.out.println("Mazgi nima bu");
            }
        }
    }


    public int changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tel raqam kiriting: ");
        String newPhone = scanner.next();
        System.out.println("Yangi password kiriting: ");
        String newPassword=scanner.next();
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE Profile set password = " + newPassword + " where phone = " + newPhone + "", Profile.class).getResultList();

        transaction.commit();
        session.close();

        return 0;
    }

    private void deleteAdminByName() {
        ProfileRepository profileRepository = new ProfileRepository();
        profileRepository.deleteProfileToDb();
    }

    private void company_card() {
        CardService cardService = new CardService();
        cardService.companyCard();
    }

    private void transaction_list() {
        TransactionService transactionService = new TransactionService();
        transactionService.get_transaction_list();

    }

    private void profile() {
        AdminProfileController profileController = new AdminProfileController();
        profileController.start();
    }

    private void terminal() {
        AdminTerminalController terminalController = new AdminTerminalController();
        terminalController.start();
    }

    private void card() {
        AdminCardController controller = new AdminCardController();
        controller.start();

    }


    private void menu() {
        System.out.println("*****  ADMIN MENU  *****");

        System.out.println("1.Card");
        System.out.println("2.Terminal");
        System.out.println("3.Profile");
        System.out.println("4.Transaction List");
        System.out.println("5.Company Card");
        System.out.println("6.Delete ADMIN");
        System.out.println("7.Change password");
        System.out.println("0.Back to Menu");
    }


}
