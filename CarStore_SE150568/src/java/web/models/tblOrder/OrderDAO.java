/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblOrder;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import web.utils.DBHelper;

/**
 *
 * @author DELL
 */
public class OrderDAO implements Serializable {

    public boolean addOrder(OrderDTO order) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();
            //2. Create query string
            String sql = "INSERT INTO "
                    + "tblOrder (orderID, customerName, userID, address, phone, purchaseDate, totalPay) "
                    + "VALUES (?,?,?,?,?,?,?)";
            //3. Create statement and assign parameters
            stm = con.prepareStatement(sql);
            stm.setString(1, order.getOrderID());
            stm.setString(2, order.getCustomerName());
            stm.setString(3, order.getUserID());
            stm.setString(4, order.getAddress());
            stm.setString(5, order.getPhone());
            stm.setString(6, order.getPurchaseDate());
            stm.setDouble(7, order.getTotalPay());

            //4. Execute query
            int row = stm.executeUpdate();
            if (row > 0) {
                result = true;
            }
        } catch (Exception e) {

        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return result;
    }

}
