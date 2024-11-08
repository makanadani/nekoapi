/*
 * @author Marina Yumi Kanadani - RM 558404 - 1TDSPX
 */

package br.com.nekoapi.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CatConnection {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USER = "rm558404";
    private static final String PASSWORD = "090790";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
