package org.example;

import org.example.controller.AuthController;
import org.example.db.DataBase;
import org.example.config.Config;
import org.example.controller.AuthController;
import org.example.db.DataBase;
import org.example.repository.TransactionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {

        DataBase.createTable();
        DataBase.adminInit();
        DataBase.addCompanyCard();

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        AuthController authController=new AuthController();
        authController.start();


 }
}
