/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblUser;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import web.utils.DBHelper;

/**
 *
 * @author DELL
 */
public class UserDAO implements Serializable {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO dto = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT userID, password, fullName, email, address, phone, roleID "
                        + " FROM tblUser "
                        + " WHERE userID=? AND password=? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String roleID = rs.getString("roleID");
                    dto = new UserDTO(userID, password, fullName, email, address, phone, roleID);
                }
            }
        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
     public UserDTO checkLoginGoogle(String email) throws SQLException {
        UserDTO dto = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT userID, password, fullName, email, address, phone, roleID "
                        + " FROM tblUser "
                        + " WHERE email=? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String userID = rs.getString("userID");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String roleID = rs.getString("roleID");
                    dto = new UserDTO(userID, password, fullName, email, address, phone, roleID);
                }
            }
        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }

    public boolean checkDuplicatedUserID(String userID) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT userID "
                        + " FROM tblUser "
                        + " WHERE userID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public boolean checkDuplicatedEmail(String email) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT email "
                        + " FROM tblUser "
                        + " WHERE email=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public boolean addUser(UserDTO user) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblUser(userID, password, fullName, email, address, phone, roleID) "
                        + "VALUES(?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getPassword());
                stm.setString(3, user.getFullName());
                stm.setString(4, user.getEmail());
                stm.setString(5, user.getAddress());
                stm.setString(6, user.getPhone());
                stm.setString(7, user.getRoleID());
                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
