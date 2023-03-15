package org.example.repository;

import org.example.db.DataBase;
import org.example.dto.Profile;
import org.example.util.ProfileUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ProfileRepository {
    public Profile searchByPhone(String phone) {


        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from profile where phone=? ;");
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return ProfileUtil.get_profile(resultSet);
            }


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

    public int addProfileToDb(Profile profile) {

        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into profile(name,surname,phone,password,created_date,status,role) " +
                            "values (?,?,?,?,?,?,?)");
            statement.setString(1, profile.getName());
            statement.setString(2, profile.getSurname());
            statement.setString(3, profile.getPhone());
            statement.setString(4, profile.getPassword());
            statement.setTimestamp(5, Timestamp.valueOf(profile.getCreated_date()));
            statement.setString(6, profile.getStatus().name());
            statement.setString(7, profile.getRole().name());

            int resultSet = statement.executeUpdate();
            return resultSet;


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

    public int deleteProfileToDb() {

        Connection connection = DataBase.getConnection();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name ADMIN: ");
        String name=scanner.next();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from profile where name=? ;");
            statement.setString(1, name);

            System.out.println("Successufull!");
            int resultSet = statement.executeUpdate();
            return resultSet;


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }


    public List<Profile> get_profile_list_fromDb() {

        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from profile ");


            ResultSet resultSet = statement.executeQuery();

            List<Profile> profileList = new LinkedList<>();
            while (resultSet.next()) {

                profileList.add(ProfileUtil.get_profile(resultSet));

            }

            return profileList;

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
}
