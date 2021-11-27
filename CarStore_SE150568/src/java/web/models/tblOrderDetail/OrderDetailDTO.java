/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblOrderDetail;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class OrderDetailDTO implements Serializable {
    private String orderID;
    private int productID;
    private int quantity;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderID, int productID, int quantity) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
