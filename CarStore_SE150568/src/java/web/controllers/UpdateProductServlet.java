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
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {

    private static final String UPDATE = "LoadProductServlet";
    private static final String VIEW_PRODUCT = "ProductDetailServlet";
    private static final String ERROR = "error.html";
    private static final String UPLOAD_DIR = "images";
    private static final Logger LOGGER = Logger.getLogger(UpdateProductServlet.class);

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
            String productIDString = request.getParameter("txtProductID");
            String productName = request.getParameter("txtProduct");
            String brandID = request.getParameter("brand");
            String priceString = request.getParameter("txtPrice");
            String quantityString = request.getParameter("txtQuantity");
            String categoryID = request.getParameter("category");
            String image = uploadFile(request);
            String statusString = request.getParameter("status");
            boolean status = Boolean.parseBoolean(statusString);
            double price = 0;
            int quantity = 0;
            int productID = Integer.parseInt(productIDString);
            try {
                price = Double.parseDouble(priceString);
            } catch (NumberFormatException e) {
                error = true;
                productError.setPriceErr("Price must be number");
            }
            try {
                quantity = Integer.parseInt(quantityString);
            } catch (NumberFormatException e) {
                error = true;
                productError.setQuantityErr("Quantity must be integer");
            }
            if (productName.trim().length() < 6) {
                error = true;
                productError.setProductNameErr("Product name must have at least 6 characters");
            }

            if (error) {
                url = UPDATE + "?id=" + productID;
                request.setAttribute("ERROR", productError);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                ProductDAO dao = new ProductDAO();
                ProductDTO productDTO = dao.getProductDetail(productID);
                if (image.trim().isEmpty()) {
                    image = productDTO.getImage();
                }
                ProductDTO dto = new ProductDTO(productID, productName, brandID, price, quantity, categoryID, image, status);
                boolean result = dao.updateProduct(dto);
                if (result) {
                    url = VIEW_PRODUCT + "?id=" + productID;
                } else {
                    url = ERROR;
                }
                response.sendRedirect(url);
            }

        } catch (Exception e) {
//            log("Error at UpdateProductServlet: " + e.toString());
            LOGGER.info("\n\n---------------------------------------------------------------------------\n");
            LOGGER.error("\nERROR at UpdateProductServlet: \n", e);
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
