package com.example;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        Connection conn = DBConnection.connect();

        if (conn != null) {
            System.out.println("Connected Successfully!");
        } else {
            System.out.println("Connection Failed!");
        }
    }
}