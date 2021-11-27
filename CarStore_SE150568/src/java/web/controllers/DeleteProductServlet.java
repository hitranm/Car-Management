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
import web.models.tblProduct.ProductDAO;
import web.models.tblUser.UserDTO;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {

    private static final String VIEW_PRODUCT = "DispatchServlet?btAction=Manage";
    private static final String ERROR = "error.html";
    private static final String LOGIN = "login.jsp";
    private static final String DENY = "accessDenied.jsp";
    private static final Logger LOGGER = Logger.getLogger(DeleteProductServlet.class);

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
        try {
            HttpSession session = request.getSession();
            UserDTO dto = (UserDTO) session.getAttribute("USER");
            if (dto == null) {
                request.setAttribute("DID_LOGIN", "You need to login to do this action");
                url = LOGIN;
            } else if (dto.getRoleID().equals("US")) {
                url = DENY;
            } else {
                String productIDString = request.getParameter("id");
                int productID = Integer.parseInt(productIDString);
                ProductDAO dao = new ProductDAO();
                boolean result = dao.deleteProduct(productID);
                if (result) {
                    url = VIEW_PRODUCT;
                }
            }
        } catch (Exception e) {
//            log("Error at DeleteProductServlet: " + e.toString());
            LOGGER.info("\n\n---------------------------------------------------------------------------\n");
            LOGGER.error("\nERROR at DeleteProductServlet: \n", e);
        } finally {
            response.sendRedirect(url);
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
