package repositories;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector{
    public static Connection connect() {
        try {
            System.out.println("connect localhost:5432/utp-10");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/utp-10",
                    "postgres",
                    "zerozero-1"
            );
            System.out.println("connected successfully");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(3);
            return null;
        }
    }
}