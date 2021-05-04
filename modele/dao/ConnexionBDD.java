package modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {

    public static Connection cnx;

    public static void startConnexion() throws SQLException {
        cnx = DriverManager.getConnection("jdbc:mariadb://db.jules1.com:33060/SIRH?user=sirh_util&password=wMyUPzH4rPZ85QBG76pufQ4wavaeFUBea4bh5HWnUZfFMdmJpmWdfYagRZ4LyHFYa");
    }

    public static void closeConnexion() {
        try {
            cnx.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
