/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.json.JSONObject;
import web.constants.LoginGoogleConstant;
import web.models.tblUser.UserDTO;

/**
 *
 * @author DELL
 */
public class LoginWithGoogleUtil {

    public static String getToken(final String code) {
        String accessToken = null;
        try {
            String response = Request.Post(LoginGoogleConstant.GET_TOKEN_LINK)
                    .bodyForm(Form.form().add("client_id", LoginGoogleConstant.CLIENT_ID)
                            .add("client_secret", LoginGoogleConstant.CLIENT_SECRET)
                            .add("redirect_uri", LoginGoogleConstant.REDIRECT_URI).add("code", code)
                            .add("grant_type", LoginGoogleConstant.GRANT_TYPE).build())
                    .execute().returnContent().asString();
            JSONObject json = new JSONObject(response);
            accessToken = json.get("access_token").toString().replaceAll("\"", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    public static String getLoginGoogle(final String accessToken) {
        UserDTO user = null;
        String email = null;
        try {
            String link = LoginGoogleConstant.GET_USER_INFO_LINK + accessToken;
            String response = Request.Get(link).execute().returnContent().asString();
            JSONObject json = new JSONObject(response);
            email = json.getString("email");
            
        } catch (Exception e) {
        }
        return email;
    }
}
