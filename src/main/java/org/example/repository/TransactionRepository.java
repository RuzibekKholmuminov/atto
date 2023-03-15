package org.example.repository;

import org.example.db.DataBase;
import org.example.dto.Transaction;
import org.example.util.TransactionUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepository {
    public int reFill(Transaction transaction) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Card set balance = balance + :balance" +
                " where number = :number");
        query.setParameter("balance", transaction.getAmount());
        query.setParameter("number", transaction.getCard_number());
        session.save(transaction);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        factory.close();
        return 0;
    }

    public List<Transaction> get_transaction_list_from_db() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        List<Transaction> transactionList = session.createQuery("SELECT a from Transaction a", Transaction.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return transactionList;
    }

    public int profile_refill(String number, Long amount) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update card set balance=balance+? where number=? ;");
            statement.setLong(1, amount);
            statement.setString(2, number);

            return statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        return 0;
    }

    public int addTransaction(Transaction transaction) {

        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into transaction(card_number,amount,type,created_date,terminal_code) values (?,?,?,?,?);");
            statement.setString(1, transaction.getCard_number());
            statement.setLong(2, transaction.getAmount());
            statement.setString(3, transaction.getTransactionType().name());
            statement.setTimestamp(4, Timestamp.valueOf(transaction.getCreated_date()));
            statement.setString(5, transaction.getTerminal_code());
            return statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        return 0;
    }

    public List<Transaction> get_profile_transaction_list_fromDb(String phone) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from transaction  where card_number in (select number from card where phone=? ) order by created_date DESC;");
            statement.setString(1, phone);

            ResultSet resultSet = statement.executeQuery();

            List<Transaction> transactionList = new LinkedList<>();
            while (resultSet.next()) {

                transactionList.add(TransactionUtil.get_terminal(resultSet));
            }

            return transactionList;

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }


        return null;
    }

    public int make_payment(Transaction transaction, String phone) {
        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("call transfer_mony(?,?,?,?,?,?,?)");
            statement.setString(1, transaction.getCard_number());
            statement.setString(2, "234");
            statement.setLong(3, 1400);
            statement.setString(4, transaction.getTransactionType().name());
            statement.setTimestamp(5, Timestamp.valueOf(transaction.getCreated_date()));
            statement.setString(6, transaction.getTerminal_code());
            statement.setString(7, phone);
            return statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }


        return 0;
    }


}
