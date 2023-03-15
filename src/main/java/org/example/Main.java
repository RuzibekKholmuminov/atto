package org.example;

import org.example.controller.AuthController;
import org.example.db.DataBase;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Main {

    public static void main(String[] args) {
//        DataBase.createTable();
//        DataBase.adminInit();
//        DataBase.addCompanyCard();

        AuthController authController=new AuthController();
        authController.start();


 }
}
