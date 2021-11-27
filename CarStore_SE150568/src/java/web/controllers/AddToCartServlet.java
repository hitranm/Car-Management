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
import web.models.Cart.CartObject;
import web.models.tblProduct.ProductDAO;
import web.models.tblProduct.ProductDTO;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private static final String ERROR = "error.html";
    private static final Logger LOGGER = Logger.getLogger(AddToCartServlet.class);

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
            HttpSession session = request.getSession(true);
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }
            String productIDString = request.getParameter("id");
            String recall = request.getParameter("url");
            Integer productID = Integer.parseInt(productIDString);
            ProductDAO dao = new ProductDAO();
            ProductDTO productDTO = dao.getProductDetail(productID);
            String productName = productDTO.getProductName();
            String brandID = productDTO.getBrandID();
            String categoryID = productDTO.getCategoryID();
            Double price = productDTO.getPrice();
            String image = productDTO.getImage();
            boolean status = productDTO.isStatus();
            int quantity = productDTO.getQuantity();
            ProductDTO dto = new ProductDTO(productID, productName, brandID, price, quantity, categoryID, image, status, 1);
            cart.addProductToCart(dto);
            session.setAttribute("CART", cart);
            url = recall;
        } catch (Exception e) {
//            log("Error at AddToCart: " + e.toString());
            LOGGER.info("\n\n---------------------------------------------------------------------------\n");
            LOGGER.error("\nERROR at AddToCartServlet: \n", e);
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
