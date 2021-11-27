/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblCarBrand;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import web.models.tblProduct.ProductDTO;
import web.utils.DBHelper;

/**
 *
 * @author DELL
 */
public class CarBrandDAO implements Serializable {
    public List<CarBrandDTO> getBrandList() throws SQLException {
        List<CarBrandDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT brandID, brandName "
                        + " FROM tblCarBrand ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String brandID = rs.getString("brandID");
                    String brandName = rs.getString("brandName");
                    list.add(new CarBrandDTO(brandID, brandName));
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
