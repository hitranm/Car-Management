/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class DispatchServlet extends HttpServlet {

    private static final String LOGIN = "LoginServlet";
    private static final String LOGOUT = "LogoutServlet";
    private static final String ERROR = "error.html";
    private static final String REGISTER = "AddUserServlet";
    private static final String PRODUCT = "ViewProductListServlet";
    private static final String ADD_PRODUCT = "AddProductServlet";
    private static final String UPDATE_PRODUCT = "UpdateProductServlet";
    private static final String DELETE_PRODUCT = "DeleteProductServlet";
    private static final String VERIFY = "VerifyOrderServlet";
    private static final String MANAGE = "ViewAllProductServlet";
    private static final String CONFIRM_ORDER = "CheckOutServlet";
    private static final Logger LOGGER = Logger.getLogger(DispatchServlet.class);

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
        PrintWriter out = response.getWriter();
        String button = request.getParameter("btAction");
        String url = ERROR;
        try {
            if ("Login".equalsIgnoreCase(button)) {
                url = LOGIN;
            } else if ("Logout".equalsIgnoreCase(button)) {
                url = LOGOUT;
            }  else if ("Register".equalsIgnoreCase(button)) {
                url = REGISTER;
            } else if ("Product".equalsIgnoreCase(button)) {
                url = PRODUCT;
            } else if ("AddProduct".equalsIgnoreCase(button)) {
                url = ADD_PRODUCT;
            } else if ("UpdateProduct".equalsIgnoreCase(button)) {
                url = UPDATE_PRODUCT;
            } else if ("DeleteProduct".equalsIgnoreCase(button)) {
                url = DELETE_PRODUCT;
            }  else if ("Checkout".equalsIgnoreCase(button)) {
                url = VERIFY;
            } else if ("Manage".equalsIgnoreCase(button)) {
                url = MANAGE;
            } else if ("Confirm".equalsIgnoreCase(button)) {
                url = CONFIRM_ORDER;
            }
        } catch (Exception e) {
            LOGGER.info("\n\n---------------------------------------------------------------------------\n");
            LOGGER.error("\nERROR at DispatchServlet: \n", e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            out.close();
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
