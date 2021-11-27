/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
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
import web.models.tblOrder.OrderDAO;
import web.models.tblOrder.OrderDTO;
import web.models.tblOrderDetail.OrderDetailDAO;
import web.models.tblOrderDetail.OrderDetailDTO;
import web.models.tblProduct.ProductDAO;
import web.models.tblProduct.ProductDTO;
import web.models.tblUser.UserDTO;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private static final String ERROR = "error.html";
    private static final String VERIFY = "verifyOrder.jsp";
    private static final String PRODUCT = "DispatchServlet?btAction=Product";
    private static final String LOGIN = "login.jsp";
    private static final String DENY = "accessDenied.jsp";
    private static final Logger LOGGER = Logger.getLogger(CheckOutServlet.class);

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

            if (user == null) {
                request.setAttribute("DID_LOGIN", "You need to login to do this action");
                url = LOGIN;
            } else if (user.getRoleID().equals("AD")) {
                url = DENY;
            } else {
                String verifyCode = (String) session.getAttribute("AUTH_CODE");
                String code = request.getParameter("code");
                if (code.equals(verifyCode)) {
                    String orderID = UUID.randomUUID().toString();
                    String fullName = request.getParameter("txtFullName");
                    String address = request.getParameter("txtAddress");
                    String phone = request.getParameter("txtPhone");
                    double totalPay = Double.parseDouble(request.getParameter("totalPay"));

//                    ---
                    String userID = user.getUserID();
                    OrderDAO orderDAO = new OrderDAO();
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    OrderDTO orderDTO = new OrderDTO(orderID, userID, fullName, address, phone, LocalDateTime.now().toString(), totalPay);
                    CartObject cart = (CartObject) session.getAttribute("CART");
                    if (cart != null) {
                        Map<Integer, ProductDTO> items = cart.getCart();
                        boolean result = orderDAO.addOrder(orderDTO);
                        if (result) {
                            for (ProductDTO value : items.values()) {
                                int productID = value.getProductID();
                                int quantity = value.getCartQuantity();
                                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderID, productID, quantity);
                                result = orderDetailDAO.saveOrderDetail(orderDetailDTO);
                                if (result) {
                                    int newQuantity = value.getQuantity() - quantity;
                                    ProductDAO productDAO = new ProductDAO();
                                    result = productDAO.updateQuantity(productID, newQuantity);
                                    if (result) {
                                        request.setAttribute("SUCCESS", "Your order has been created successfully");
                                        url = PRODUCT;
                                        session.removeAttribute("CART");
                                    }
                                }
                            }
                        }
                    }
                } else {
                    request.setAttribute("WRONG_CODE", "Incorrect confirmation code! Please try again!");
                    url = VERIFY;
                }
            }

        } catch (Exception e) {
//            log("Error at CheckOutServlet: " + e.toString());
            LOGGER.info("\n\n---------------------------------------------------------------------------\n");
            LOGGER.error("\nERROR at CheckOutServlet: \n", e);
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
