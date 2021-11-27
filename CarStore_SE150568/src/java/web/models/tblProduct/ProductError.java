/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblProduct;

/**
 *
 * @author DELL
 */
public class ProductError {
    private String productNameErr;
    private String priceErr;
    private String quantityErr;
    private String imgErr;

    public ProductError() {
    }

    public ProductError(String productNameErr, String priceErr, String quantityErr, String imgErr) {
        this.productNameErr = productNameErr;
        this.priceErr = priceErr;
        this.quantityErr = quantityErr;
        this.imgErr = imgErr;
    }

    public String getProductNameErr() {
        return productNameErr;
    }

    public void setProductNameErr(String productNameErr) {
        this.productNameErr = productNameErr;
    }

    public String getPriceErr() {
        return priceErr;
    }

    public void setPriceErr(String priceErr) {
        this.priceErr = priceErr;
    }

    public String getQuantityErr() {
        return quantityErr;
    }

    public void setQuantityErr(String quantityErr) {
        this.quantityErr = quantityErr;
    }

    public String getImgErr() {
        return imgErr;
    }

    public void setImgErr(String imgErr) {
        this.imgErr = imgErr;
    }
    
}
