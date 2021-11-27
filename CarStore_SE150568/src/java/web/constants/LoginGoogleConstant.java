/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.constants;

/**
 *
 * @author DELL
 */
public class LoginGoogleConstant {
    public static final String CLIENT_ID = "412192120002-fhsalu92rol4m4kh33ef74ucrldi35re.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "9dxESkJFQmKqUe5NQkdWYzxn";
    public static final String REDIRECT_URI = "http://localhost:8084/CarStore_SE150568/LoginGoogleServlet";
    public static final String GET_TOKEN_LINK = "https://accounts.google.com/o/oauth2/token";
    public static final String GET_USER_INFO_LINK = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static final String GRANT_TYPE = "authorization_code";
    public static final String LOGIN_GOOGLE_LINK = "https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/CarStore_SE150568/LoginGoogleServlet&response_type=code"
    +"&client_id=412192120002-fhsalu92rol4m4kh33ef74ucrldi35re.apps.googleusercontent.com&approval_prompt=force";
}
