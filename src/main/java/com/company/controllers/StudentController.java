package com.company.controllers;

import com.company.dbhelper.DBConnection;
import com.company.objects.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentController {

    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean addNewStudent() {
        System.out.print("Enter the name of the student: ");
        String name = scanner.next();

        System.out.print("Enter the age of the student: ");
        int age = scanner.nextInt();

        try {
            ps = DBConnection.getConnection().prepareStatement("INSERT INTO students(name, age)" +
                    " VALUES('" + name + "'," + age + ")");

            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static Student getStudentById() {

        System.out.print("Enter the id of the student: ");
        int id = scanner.nextInt();
        try {
            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM students WHERE id=" + id);
            rs = ps.executeQuery();

            int studentId, age;
            String name;

            Student student = new Student();

            while (rs.next()) {
                studentId = rs.getInt("id");
                name = rs.getString("name");
                age = rs.getInt("age");
                student.setId(studentId);
                student.setName(name);
                student.setAge(age);
            }

            return student;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static void addStudentScore() {
        System.out.print("Enter the student's id: ");
        int id = scanner.nextInt();

        System.out.print("Enter the mathematics score: ");
        int mathScore = scanner.nextInt();

        System.out.print("Enter the English score: ");
        int englishScore = scanner.nextInt();

        try {
            ps = DBConnection.getConnection().prepareStatement("INSERT INTO scores (Mathematics, English, student_id) " +
                    "VALUES(" + mathScore + ", " + englishScore + ", " + id + ")");
            ps.execute();
            System.out.println("Successfully added score.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeStudentScore() {
        System.out.print("Enter the student's id: ");
        int id = scanner.nextInt();

        System.out.print("Which score you would like to remove: Mathematics, English or both? ");
        String answer = scanner.next();

        switch (answer) {
            case "Mathematics":
                try {
                    ps = DBConnection.getConnection().prepareStatement("UPDATE scores " +
                            "SET  Mathematics = 0 " +
                            "WHERE student_id = " + id);
                    ps.execute();
                    System.out.println("Successfully deleted Mathematics score for student " + id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "English":
                try {
                    ps = DBConnection.getConnection().prepareStatement("UPDATE scores " +
                            "SET English = 0 " +
                            "WHERE student_id = " + id);
                    ps.execute();
                    System.out.println("Successfully deleted English score for student " + id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "both":
                try {
                    ps = DBConnection.getConnection().prepareStatement("DELETE FROM scores " +
                            "WHERE student_id = " + id);
                    ps.execute();
                    System.out.println("Successfully deleted all scores for student " + id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public static void editScore() {
        System.out.print("Enter the student's id: ");
        int id = scanner.nextInt();

        System.out.print("Which score you would like to edit: Mathematics, English or both? ");
        String subject = scanner.next();

        switch (subject) {
            case "Mathematics":
                System.out.print("Please enter new Mathematics score for student " + id + ": ");
                int newMathScore = scanner.nextInt();
                try {
                    ps = DBConnection.getConnection().prepareStatement("UPDATE scores " +
                            "SET " + subject + " = " + newMathScore +
                            " WHERE student_id = " + id);
                    ps.execute();
                    System.out.println("Successfully updated Mathematics score for student " + id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "English":
                System.out.print("Please enter new English score for student " + id + ": ");
                int newEnglishScore = scanner.nextInt();
                try {
                    ps = DBConnection.getConnection().prepareStatement("UPDATE scores " +
                            "SET English = " + newEnglishScore +
                            " WHERE student_id = " + id);
                    ps.execute();
                    System.out.println("Successfully updated English score for student " + id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "both":
                System.out.print("Please enter new Mathematics score for student " + id + ": ");
                newMathScore = scanner.nextInt();
                System.out.print("Please enter new English score for student " + id + ": ");
                newEnglishScore = scanner.nextInt();
                try {
                    ps = DBConnection.getConnection().prepareStatement("UPDATE scores " +
                            "SET  Mathematics = " + newMathScore + ", English = " + newEnglishScore +
                            " WHERE student_id = " + id);
                    ps.execute();
                    System.out.println("Successfully updated both scores for student " + id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public static boolean deleteStudent() {
        System.out.print("Enter the student's id: ");
        int id = scanner.nextInt();

        try {
            ps = DBConnection.getConnection().prepareStatement("DELETE FROM students " +
                    "WHERE id = " + id);
            ps.execute();
            System.out.println("Successfully deleted information about student " + id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void editStudent() {
        System.out.print("Enter the student's id: ");
        int id = scanner.nextInt();

        System.out.print("What you would like to edit: name, age  or both? ");
        String answer = scanner.next();

        switch (answer) {
            case "name":
                System.out.print("Please enter new name for student " + id + ": ");
                String newName = scanner.next();
                try {
                    ps = DBConnection.getConnection().prepareStatement("UPDATE students " +
                            "SET name = '" + newName + "'" +
                            " WHERE id = " + id);
                    ps.execute();
                    System.out.println("Successfully updated name for student " + id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "age":
                System.out.print("Please enter new age for student " + id + ": ");
                int newAge = scanner.nextInt();
                try {
                    ps = DBConnection.getConnection().prepareStatement("UPDATE students " +
                            "SET age = " + newAge +
                            " WHERE id = " + id);
                    ps.execute();
                    System.out.println("Successfully updated age for student " + id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "both":
                System.out.print("Please enter new Name for student " + id + ": ");
                newName = scanner.next();
                System.out.print("Please enter new age for student " + id + ": ");
                newAge = scanner.nextInt();
                try {
                    ps = DBConnection.getConnection().prepareStatement("UPDATE students " +
                            "SET name = '" + newName + "', age = " + newAge +
                            " WHERE id = " + id);
                    ps.execute();
                    System.out.println("Successfully updated name and age for student " + id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
