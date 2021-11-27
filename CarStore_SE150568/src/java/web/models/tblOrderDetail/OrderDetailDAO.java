/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblOrderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import web.utils.DBHelper;

/**
 *
 * @author DELL
 */
public class OrderDetailDAO implements Serializable {

    public boolean saveOrderDetail(OrderDetailDTO orderDetail) {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblOrderDetail "
                        + "(orderID, productID, quantity) "
                        + "VALUES (?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderDetail.getOrderID());
                stm.setInt(2, orderDetail.getProductID());
                stm.setInt(3, orderDetail.getQuantity());
                int row = stm.executeUpdate();

                if (row > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
        }
        return result;
    }
}
