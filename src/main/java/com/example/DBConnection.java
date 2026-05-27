/*
Name: Umandap, Sam Matthew B.
Section: BSIT 2-2
File Name: DBConnection.java
*/

package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connect() {

        try {

            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/studentdb",
                    "postgres",
                    "admin123"
            );

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}