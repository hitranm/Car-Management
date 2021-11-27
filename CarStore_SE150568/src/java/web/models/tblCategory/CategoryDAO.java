/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblCategory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import web.utils.DBHelper;

/**
 *
 * @author DELL
 */
public class CategoryDAO implements Serializable {
    public List<CategoryDTO> getCategoryList() throws SQLException {
        List<CategoryDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT categoryID, categoryName "
                        + " FROM tblCategory ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    list.add(new CategoryDTO(categoryID, categoryName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return list;
    }
}
