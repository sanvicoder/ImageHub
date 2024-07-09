package com.image.frontend;

import com.image.models.Image;
import com.image.backend.ImageHandling;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GetImage extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
        } else {
            ImageHandling imageManagement = new ImageHandling();
            String imageId = request.getParameter("imageId");
            Image image = imageManagement.getImage(imageId);
            if (image != null) {
                response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
                try {
                    response.getOutputStream().flush();
                    response.getOutputStream().write(image.getPhoto());
                    response.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}