/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.Cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import web.models.tblProduct.ProductDAO;
import web.models.tblProduct.ProductDTO;

/**
 *
 * @author DELL
 */
public class CartObject {

//    public CartObject() {
//        super();
//    }
//
//    public void addProductToCart(ProductDTO productDTO) {
//
//        int key = productDTO.getProductID();
//        if (this.containsKey(key)) {
//            int oldQuantity = ((ProductDTO) this.get(key)).getCartQuantity();
//            ((ProductDTO) this.get(key)).setCartQuantity(oldQuantity + 1);
//        } else {
//            this.put(key, productDTO);
//        }
//    }
//
//    public boolean removeProduct(int productID) {
//        if (this.containsKey(productID)) {
//            this.remove(productID);
//            return true;
//        }
//        return false;
//    }
//
//    public void decreaseQuantity(int productID) {
//        if (this.containsKey(productID)) {
//            int oldQuantity = ((ProductDTO) this.get(productID)).getCartQuantity();
//            ((ProductDTO) this.get(productID)).setCartQuantity(oldQuantity - 1);
//        }
//    }
    private Map<Integer, ProductDTO> cart;

    public CartObject() {
    }

    public CartObject(Map<Integer, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<Integer, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, ProductDTO> cart) {
        this.cart = cart;
    }

    public boolean addProductToCart(ProductDTO product) {
        boolean check = false;
        try {
            if (cart == null) {
                cart = new HashMap<>();
            }
            if (cart.containsKey(product.getProductID())) {
                int quantity = cart.get(product.getProductID()).getCartQuantity();
                product.setCartQuantity(quantity + 1);
            }
            cart.put(product.getProductID(), product);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

    public boolean removeProduct(int productID) {
        boolean check = false;
        try {
            if (cart != null) {
                if (cart.containsKey(productID)) {
                    cart.remove(productID);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }

    public void decreaseQuantity(int productID) throws SQLException {
        if (cart.containsKey(productID)) {
            int quantity = cart.get(productID).getCartQuantity();
            cart.get(productID).setCartQuantity(quantity - 1);
        }
    }
}
