/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import web.models.tblUser.UserDAO;
import web.models.tblUser.UserDTO;
import web.models.tblUser.UserError;

/**
 *
 * @author DELL
 */
public class AddUserServlet extends HttpServlet {

    private static final String REGISTER = "register.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String ERROR = "error.jsp";
    private static final Logger LOGGER = Logger.getLogger(AddUserServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userid = request.getParameter("userid");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNum");
        String address = request.getParameter("address");
        String roleID = request.getParameter("roleID");
        boolean error = false;
        String url = ERROR;
        UserError userError = new UserError();
        UserDAO dao = new UserDAO();
        try {
            if (userid.trim().length() < 6 || userid.trim().length() > 50) {
                error = true;
                userError.setUserIdError("User ID's length is 6-50 characters!");
            }
            if (dao.checkDuplicatedUserID(userid)) {
                error = true;
                userError.setUserIdError("This user ID is existed!");
            }
            if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
                error = true;
                userError.setEmailError("Email is invalid!");
            }
            if (dao.checkDuplicatedEmail(email)) {
                error = true;
                userError.setEmailError("This email is used!");
            }
            if (!phone.matches("^[0-9]{10}$")) {
                error = true;
                userError.setPhoneError("Phone number must contain 10 numbers");
            }
            if (password.trim().length() < 6) {
                error = true;
                userError.setPasswordError("Password must contain at least 6 characters.");
            }
            if (!password.equals(cpassword)) {
                error = true;
                userError.setPasswordError("Confirm password does not match.");
            }
            if (error) {
                url = REGISTER;
                request.setAttribute("ERROR", userError);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                UserDTO user = new UserDTO(userid, password, fullName, email, address, phone, roleID);
                boolean result = dao.addUser(user);
                if (result) {
                    url = LOGIN;
                }
                response.sendRedirect(url);
            }
        } catch (Exception e) {
            LOGGER.info("\n\n---------------------------------------------------------------------------\n");
            LOGGER.error("\nERROR at AddUserServlet: \n", e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
