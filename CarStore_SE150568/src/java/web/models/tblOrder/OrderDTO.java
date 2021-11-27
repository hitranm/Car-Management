/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblOrder;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class OrderDTO implements Serializable {

    private String orderID;
    private String userID;
    private String customerName;
    private String address;
    private String phone;
    private String purchaseDate;
    private double totalPay;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String userID, String customerName, String address, String phone, String purchaseDate, double totalPay) {
        this.orderID = orderID;
        this.userID = userID;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.purchaseDate = purchaseDate;
        this.totalPay = totalPay;
    }

    public OrderDTO(String userID, String customerName, String address, String phone, String purchaseDate, double totalPay) {
        this.userID = userID;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.purchaseDate = purchaseDate;
        this.totalPay = totalPay;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }


}
