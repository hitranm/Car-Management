/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblProduct;

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
public class ProductDAO implements Serializable {

    public List<ProductDTO> get3Product() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 3 productID, productName, brandID, price, "
                        + "quantity, categoryID, image, status "
                        + " FROM tblProduct "
                        + " WHERE quantity>0 AND STATUS = 1 ORDER BY productID ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Integer productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String brandID = rs.getString("brandID");
                    Double price = rs.getDouble("price");
                    Integer quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    list.add(new ProductDTO(productID, productName, brandID, price, quantity, categoryID, image, status));
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

    public List<ProductDTO> getProductList() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT productID, productName, brandID, price, "
                        + "quantity, categoryID, image, status "
                        + " FROM tblProduct "
                        + " WHERE quantity>0 AND status=1";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Integer productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String brandID = rs.getString("brandID");
                    Double price = rs.getDouble("price");
                    Integer quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    list.add(new ProductDTO(productID, productName, brandID, price, quantity, categoryID, image, status));
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

    public boolean addProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            String sql = "INSERT INTO "
                    + "tblProduct (productName, brandID, price, quantity, categoryID, image, status) "
                    + "VALUES (?,?,?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, productDTO.getProductName());
            stm.setString(2, productDTO.getBrandID());
            stm.setDouble(3, productDTO.getPrice());
            stm.setInt(4, productDTO.getQuantity());
            stm.setString(5, productDTO.getCategoryID());
            stm.setString(6, productDTO.getImage());
            stm.setBoolean(7, productDTO.isStatus());
            int row = stm.executeUpdate();
            if (row > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public ProductDTO getProductDetail(Integer productID) throws SQLException {
        ProductDTO product = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT productID, productName, brandID, price, "
                        + "quantity, categoryID, image, status "
                        + " FROM tblProduct "
                        + " WHERE productID=?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    String brandID = rs.getString("brandID");
                    Double price = rs.getDouble("price");
                    Integer quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    product = new ProductDTO(productID, productName, brandID, price, quantity, categoryID, image, status);
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
        return product;
    }

    public List<ProductDTO> searchProduct(String searchValue) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT productID, productName, brandID, price, "
                        + "quantity, categoryID, image, status "
                        + " FROM tblProduct "
                        + " WHERE productName like ? and quantity>0 AND status=1 ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    Integer productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String brandID = rs.getString("brandID");
                    Double price = rs.getDouble("price");
                    Integer quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    list.add(new ProductDTO(productID, productName, brandID, price, quantity, categoryID, image, status));
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

    public boolean updateProduct(ProductDTO product) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblProduct "
                        + "SET productName=?, brandID=?, price=?, quantity=?, categoryID=?, image=?, status=? "
                        + "Where productID=?";

                stm = con.prepareStatement(sql);
                stm.setString(1, product.getProductName());
                stm.setString(2, product.getBrandID());
                stm.setDouble(3, product.getPrice());
                stm.setInt(4, product.getQuantity());
                stm.setString(5, product.getCategoryID());
                stm.setString(6, product.getImage());
                stm.setBoolean(7, product.isStatus());
                stm.setInt(8, product.getProductID());
                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean deleteProduct(int productID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            conn = DBHelper.makeConnection();
            if (conn != null) {
                String sql = "UPDATE tblProduct "
                        + "SET status=0 "
                        + "Where productID=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, productID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean updateQuantity(int productID, int quantity) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblProduct "
                        + "SET quantity=? "
                        + "Where productID=?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, productID);

                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public List<ProductDTO> getAllProduct() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT productID, productName, brandID, price, "
                        + "quantity, categoryID, image, status "
                        + " FROM tblProduct ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Integer productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String brandID = rs.getString("brandID");
                    Double price = rs.getDouble("price");
                    Integer quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    list.add(new ProductDTO(productID, productName, brandID, price, quantity, categoryID, image, status));
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
