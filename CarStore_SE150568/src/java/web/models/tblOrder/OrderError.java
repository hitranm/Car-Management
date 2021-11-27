/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblOrder;

/**
 *
 * @author DELL
 */
public class OrderError {
    private String customerNameError;
    private String addressError;
    private String phoneError;
    private String quantityError;

    public OrderError() {
    }

    public OrderError(String customerNameError, String addressError, String phoneError, String quantityError) {
        this.customerNameError = customerNameError;
        this.addressError = addressError;
        this.phoneError = phoneError;
        this.quantityError = quantityError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }    

    public String getCustomerNameError() {
        return customerNameError;
    }

    public void setCustomerNameError(String customerNameError) {
        this.customerNameError = customerNameError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }
    
}
