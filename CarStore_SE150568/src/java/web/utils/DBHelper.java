/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() throws SQLException, ClassNotFoundException {
        Connection con = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url="jdbc:sqlserver://localhost:1433;databaseName=CarStore";
        con=DriverManager.getConnection(url, "sa", "sa");
        return con;
    }
    
}
