package com.image.frontend;

import com.image.models.User;
import com.image.backend.ImageHandling;
import com.image.backend.UserHandling;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ImageDelete extends HttpServlet {

    private static final long serialVersionUID = 1L;


    public ImageDelete() {
        super();
    }

    /**
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
        } else {
            UserHandling login = new UserHandling();
            ImageHandling imageManagement = new ImageHandling();
            String imageid = request.getParameter("imageid").toString();
            imageManagement.deleteImage(imageid);
            System.out.println(imageid + "deleted");
            User userUpdated = login.getUserDetails(((User) request.getSession().getAttribute("user")).getUsername());
            request.getSession().setAttribute("user", userUpdated);
            response.sendRedirect("userhome.jsp");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}