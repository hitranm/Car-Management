/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import web.models.tblCarBrand.CarBrandDAO;
import web.models.tblCarBrand.CarBrandDTO;
import web.models.tblCategory.CategoryDAO;
import web.models.tblCategory.CategoryDTO;
import web.models.tblProduct.ProductDAO;
import web.models.tblProduct.ProductDTO;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/ProductDetailServlet"})
public class ProductDetailServlet extends HttpServlet {

    private static final String PRODUCT_DETAIL = "productDetail.jsp";
    private static final String ERROR = "error.html";
    private static final Logger LOGGER = Logger.getLogger(ProductDetailServlet.class);

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
            String productIDString = request.getParameter("id");
            int productID = Integer.parseInt(productIDString);
            ProductDAO productDAO = new ProductDAO();
            ProductDTO product = productDAO.getProductDetail(productID);
            CarBrandDAO carBrandDAO = new CarBrandDAO();
            List<CarBrandDTO> brandDTOs = carBrandDAO.getBrandList();
            CategoryDAO categoryDAO = new CategoryDAO();
            List<CategoryDTO> categoryDTOs = categoryDAO.getCategoryList();
            request.setAttribute("CATEGORY", categoryDTOs);
            request.setAttribute("BRAND", brandDTOs);
            request.setAttribute("PRODUCT", product);
            url = PRODUCT_DETAIL;
        } catch (Exception e) {
//            log("Error at ProductDetailServlet" + e.toString());
            LOGGER.info("\n\n---------------------------------------------------------------------------\n");
            LOGGER.error("\nERROR at ProductDetailServlet: \n", e);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
