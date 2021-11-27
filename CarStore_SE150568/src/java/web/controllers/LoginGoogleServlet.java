/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import web.models.tblUser.UserDAO;
import web.models.tblUser.UserDTO;
import web.utils.LoginWithGoogleUtil;

/**
 *
 * @author DELL
 */
@WebServlet(name = "LoginGoogleServlet", urlPatterns = {"/LoginGoogleServlet"})
public class LoginGoogleServlet extends HttpServlet {

    private static final String ERROR = "error.html";
    private static final String HOMEPAGE = "homePage.jsp";
    private static final String LOGIN = "login.jsp";
    private static final Logger LOGGER = Logger.getLogger(LoginGoogleServlet.class);

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
        String url = ERROR;
        HttpSession session = request.getSession();
        try {
            String code = request.getParameter("code");
            if (code != null && !code.isEmpty()) {
                String accessToken = LoginWithGoogleUtil.getToken(code);
                String email = LoginWithGoogleUtil.getLoginGoogle(accessToken);
                if (email == null) {
                    request.setAttribute("ERROR", "null google");
                    url = LOGIN;
                }
                UserDAO dao = new UserDAO();
                UserDTO user = dao.checkLoginGoogle(email);
                if (user == null) {
                    request.setAttribute("ERROR", "This account is not existed!");
                    url = LOGIN;
                } else {
                    session.setAttribute("USER", user);
                    url = HOMEPAGE;
                }
            }
        } catch (Exception e) {
//            log("Error at LoginGoogleServlet: " + e.toString());
            LOGGER.info("\n\n---------------------------------------------------------------------------\n");
            LOGGER.error("\nERROR at LoginGoogleServlet: \n", e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
