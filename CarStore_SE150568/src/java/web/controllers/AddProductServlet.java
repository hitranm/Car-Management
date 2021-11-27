/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import web.models.tblProduct.ProductDAO;
import web.models.tblProduct.ProductDTO;
import web.models.tblProduct.ProductError;

/**
 *
 * @author DELL
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
public class AddProductServlet extends HttpServlet {

    private static final String ADD_PRODUCT = "addProduct.jsp";
    private static final String ERROR = "error.html";
    private static final String VIEW_PRODUCT = "ViewProductListServlet";
    private static final String UPLOAD_DIR = "images";
//    private static final Logger LOGGER = Logger.getLogger(AddProductServlet.class);

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
        boolean error = false;
        try {
            ProductError productError = new ProductError();
            String productName = request.getParameter("txtProduct");
            String brandID = request.getParameter("brand");
            String priceString = request.getParameter("txtPrice");
            String quantityString = request.getParameter("txtQuantity");
            String categoryID = request.getParameter("category");
            String image = uploadFile(request);
            double price = 0;
            int quantity = 0;
            try {
                price = Double.parseDouble(priceString);
            } catch (NumberFormatException e) {
                error = true;
                productError.setPriceErr("Price must be number");
            }
            if (price <= 0) {
                error = true;
                productError.setPriceErr("Price must bigger than 0");
            }
            try {
                quantity = Integer.parseInt(quantityString);
            } catch (NumberFormatException e) {
                error = true;
                productError.setQuantityErr("Quantity must be integer");
            }
            if (quantity < 1) {
                error = true;
                productError.setQuantityErr("Quantity must be at least 1");
            }
            if (productName.trim().length() < 6 || productName.trim().length() > 50) {
                error = true;
                productError.setProductNameErr("Product name must have from 6 to 50 characters");
            }
            if (image.trim().isEmpty()) {
                error = true;
                productError.setImgErr("Please upload an image");
            }
            if (error) {
                url = ADD_PRODUCT;
                request.setAttribute("ADD_PRODUCT_ERROR", productError);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                ProductDAO dao = new ProductDAO();
                ProductDTO dto = new ProductDTO(productName, brandID, price, quantity, categoryID, image, true);
                boolean result = dao.addProduct(dto);
                if (result) {
                    url = VIEW_PRODUCT;
                }
                response.sendRedirect(url);
            }

        } catch (Exception e) {
            log("error" + e.toString());
//            LOGGER.info("\n\n---------------------------------------------------------------------------\n");
//            LOGGER.error("\nERROR at AddProductServlet: \n", e);
        }
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName;
        try {
            Part filePart = request.getPart("image");
            fileName = (String) getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            int end = applicationPath.lastIndexOf("build");
            String truePath = applicationPath.substring(0, end) + "web";
            String basePath = truePath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
