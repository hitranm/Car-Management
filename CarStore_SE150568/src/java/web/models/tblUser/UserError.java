/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.models.tblUser;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class UserError implements Serializable {

    private String userIdError;
    private String passwordError;
    private String emailError;
    private String phoneError;

    public UserError() {
    }

    public UserError(String userIdError, String passwordError, String emailError, String phoneError) {
        this.userIdError = userIdError;
        this.passwordError = passwordError;
        this.emailError = emailError;
        this.phoneError = phoneError;
    }

    public String getUserIdError() {
        return userIdError;
    }

    public void setUserIdError(String userIdError) {
        this.userIdError = userIdError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

}
