/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblCarBrand;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class CarBrandDTO implements Serializable {
    private String brandID;
    private String brandName;

    public CarBrandDTO() {
    }

    public CarBrandDTO(String brandID, String brandName) {
        this.brandID = brandID;
        this.brandName = brandName;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    
}
