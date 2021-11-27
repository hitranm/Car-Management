/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import web.models.Cart.CartObject;
import web.models.tblOrder.OrderError;
import web.models.tblOrderDetail.OrderDetailDTO;
import web.models.tblProduct.ProductDAO;
import web.models.tblProduct.ProductDTO;
import web.models.tblUser.UserDTO;
import web.utils.SendEmailUtil;

/**
 *
 * @author DELL
 */
@WebServlet(name = "VerifyOrderServlet", urlPatterns = {"/VerifyOrderServlet"})
public class VerifyOrderServlet extends HttpServlet {

    private static final String ERROR = "error.html";
    private static final String CART_PAGE = "cartPage.jsp";
    private static final String VERIFY = "verifyOrder.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String DENY = "accessDenied.jsp";
    private static final Logger LOGGER = Logger.getLogger(VerifyOrderServlet.class);

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
            UserDTO user = (UserDTO) session.getAttribute("USER");
            OrderError orderError = new OrderError();
            boolean error = false;
            String email = request.getParameter("txtEmail");
            String fullName = request.getParameter("txtFullName");
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");
            double totalPay = Double.parseDouble(request.getParameter("totalPay"));
            if (user == null) {
                request.setAttribute("DID_LOGIN", "You need to login to do this action");
                url = LOGIN;
            } else if (user.getRoleID().equals("AD")) {
                url = DENY;
            } else {
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    Map<Integer, ProductDTO> items = cart.getCart();
                    for (ProductDTO value : items.values()) {
                        int cartQuantity = value.getCartQuantity();
                        int quantity = value.getQuantity();
                        if (cartQuantity > quantity) {
                            error = true;
                            orderError.setQuantityError("The number of cars in cart is more than the number in stock.");
                        }
                    }
                }
                if (!phone.matches("^[0-9]{10}$")) {
                    error = true;
                    orderError.setPhoneError("Phone number must contain 10 numbers");
                }
                if (fullName.trim().length() < 3 || fullName.trim().length() > 50) {
                    error = true;
                    orderError.setCustomerNameError("Fullname must contain from 3 to 50 characters.");
                }
                if (address.trim().length() < 5) {
                    error = true;
                    orderError.setAddressError("Address must be longer than 5 characters");
                }
                if (error) {
                    request.setAttribute("ORDER_ERROR", orderError);
                    url = CART_PAGE;
                } else {
                    SendEmailUtil sm = new SendEmailUtil();
                    String code = sm.getRandom();
                    boolean result = sm.sendEmail(code, email);
                    if (result) {
                        session.setAttribute("AUTH_CODE", code);
                        url = VERIFY;
                    } else {
                        url = CART_PAGE;
                        request.setAttribute("SEND_ERROR", "Cannot send confirmation to email" + email);
                    }
                }
            }
        } catch (Exception e) {
//            log("Error at VerifyOrderServlet: " + e.toString());
            LOGGER.info("\n\n---------------------------------------------------------------------------\n");
            LOGGER.error("\nERROR at VerifyOrderServlet: \n", e);
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
