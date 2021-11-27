/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblProduct;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class ProductDTO implements Serializable {

    private int productID;
    private String productName;
    private String brandID;
    private double price;
    private int quantity;
    private String categoryID;
    private String image;
    private boolean status;

    public ProductDTO() {
    }
    private int cartQuantity;

    public ProductDTO(int productID, String productName, String brandID, double price, int quantity, String categoryID, String image, boolean status, int cartQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.brandID = brandID;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.image = image;
        this.status = status;
        this.cartQuantity = cartQuantity;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public ProductDTO(int productID, String productName, String brandID, double price, int quantity, String categoryID, String image, boolean status) {
        this.productID = productID;
        this.productName = productName;
        this.brandID = brandID;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.image = image;
        this.status = status;
    }

    public ProductDTO(String productName, String brandID, double price, int quantity, String categoryID, String image, boolean status) {
        this.productName = productName;
        this.brandID = brandID;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.image = image;
        this.status = status;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
