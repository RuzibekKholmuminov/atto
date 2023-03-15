package org.example.db;

import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.enums.GeneralStatus;
import org.example.enums.ProfileRole;
import org.example.repository.CardRepository;
import org.example.repository.ProfileRepository;
import org.example.util.MD5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

public class DataBase {
    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Atto", "Umid",
                    "123");
            return con;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
            System.exit(-1);
        }
        return null;

    }


    public static void createTable() {
        createProfileTable();
        createCardTable();
        createTerminalTable();
        createTransactionTable();
        createProfile_CardTable();
        createPayment_Procedure();
    }


    private static void createProfileTable() {

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "create table if not exists profile( id serial primary key, " +
                    "name varchar(20) ," +
                    " surname varchar(20) ," +
                    "  phone varchar(12) unique not null, " +
                    "password varchar, " +
                    "created_date timestamp default now(), " +
                    "status varchar, role varchar ) ;";
            statement.executeUpdate(sql);


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


    }

    private static void createCardTable() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "create table if not exists card( id serial primary key, " +
                    "number varchar(20)  unique not null," +
                    " exp_date date  ," +
                    "  balance bigint, " +
                    "status varchar, " +
                    "phone varchar(12) , " +
                    "created_date timestamp default now()) ;";
            statement.executeUpdate(sql);


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

    }

    private static void createTerminalTable() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "create table if not exists terminal( id serial primary key, " +
                    "code varchar unique not null ," +
                    "address varchar ," +
                    "status varchar, " +
                    "created_date timestamp default now()) ;";
            statement.executeUpdate(sql);


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

    }


    private static void createTransactionTable() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "create table if not exists transaction( id serial primary key, " +
                    "card_number varchar not null, " +
                    "amount bigint , " +
                    "terminal_code varchar , " +
                    "type varchar , " +
                    "created_date timestamp default now()," +
                    " foreign key(card_number) references  card(number), " +
                    " foreign key(terminal_code) references  terminal(code)) ;";
            statement.executeUpdate(sql);


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

    }

    private static void createProfile_CardTable() {

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "create table if not exists profile_card( id serial primary key, " +
                    "profile_phone varchar(12) ," +
                    "card_number varchar ," +
                    "added_date timestamp default now() ) ";
            statement.executeUpdate(sql);


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


    }


    private static void createPayment_Procedure() {

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            String sql = "CREATE OR replace procedure transfer_mony (from_card varchar, to_card varchar, amount dec,type varchar,created_date timestamp,terminal_code varchar,u_phone varchar)\n" +
                    "language plpgsql\n" +
                    "AS $$\n" +
                    "declare\n" +
                    "current_balance  dec;\n" +
                    "   begin\n" +
                    "  \n" +
                    "  update card set balance = balance - amount where number = from_card;\n" +
                    "  update card set balance = balance + amount where number = to_card;\n" +
                    "  \n" +
                    "  select balance into current_balance from card where number = from_card;\n" +
                    "  \n" +
                    "  \n" +
                    "  if current_balance < amount and from_card in(select number from card where phone=u_phone)\n" +
                    "  then \n" +
                    "    rollback;\n" +
                    "    return;\n" +
                    "  end if;  \n" +
                    "  \n" +
                    "  commit;\n" +
                    "  insert into transaction(card_number,amount,type,created_date,terminal_code) values (from_card,amount,type,created_date,terminal_code);\n" +
                    "end; $$";
            statement.executeUpdate(sql);


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


    }


    public static void adminInit() {

        Profile profile = new Profile("Ruzi", "Xolmuminov", "123", "123",
                LocalDateTime.now(), GeneralStatus.ACTIVE, ProfileRole.ADMIN);

        ProfileRepository profileRepository = new ProfileRepository();

        Profile profile2 = profileRepository.searchByPhone(profile.getPhone());
        if (profile2 != null) {
            return;
        }

        profileRepository.addProfileToDb(profile);


    }

    public static void addCompanyCard() {

        Card card = new Card("999", null, 0L, GeneralStatus.ACTIVE.toString(), null, LocalDateTime.now());
        CardRepository cardRepository = new CardRepository();
        List<Card> card1 = cardRepository.searchCardByNumber(card.getNumber());

        if (card1.get(0) == null) {
            return;
        }
        cardRepository.addCardToDb(card);


    }

}
