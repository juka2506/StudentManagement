package com.company.login;

import com.company.dbhelper.DBConnection;
import com.company.objects.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean signUp() {
        System.out.print("Enter the username: ");
        String username = scanner.next().trim();

        System.out.print("Enter the password: ");
        String password = scanner.next().trim();

        try {
            ps = DBConnection.getConnection().prepareStatement("INSERT INTO users(username, password)" +
                    " VALUES('" + username + "','" + password + "')");

            ps.execute();
            return true;
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("Unable to sign up, username probably already exists. Try again.");
            return false;
        }
    }

    public static boolean login() {

        System.out.print("Enter the username: ");
        String username = scanner.next().trim();

        System.out.print("Enter the password: ");
        String password = scanner.next().trim();

        try {
            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM users " +
                    "WHERE username='" + username + "'");
            rs = ps.executeQuery();

            String passwordCheck = "";
            while (rs.next()) {
                passwordCheck = rs.getString("password");
                }
            return passwordCheck.equals(password);


        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Unable to login.");
            return false;
        }
    }
}
