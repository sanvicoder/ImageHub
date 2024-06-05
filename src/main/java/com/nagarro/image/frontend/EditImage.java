package com.nagarro.image.frontend;

import com.nagarro.image.models.Image;
import com.nagarro.image.models.User;
import com.nagarro.image.backend.ImageHandling;
import com.nagarro.image.backend.UserHandling;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditImage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
        } else {
            UserHandling login = new UserHandling();
            String imageId = request.getParameter("imageId");
            ImageHandling imageManagement = new ImageHandling();
            Image img = imageManagement.getImage(imageId);
            float currentImageSize = (float) img.getImageSize();
            double imageSize = 0;
            byte[] bytes = null;
            String imageName = null;

            response.setContentType("text/html;charset=UTF-8");
            try {
                if (!ServletFileUpload.isMultipartContent(request)) {
                    System.out.println("sorry. No file uploaded");
                    return;
                }

                // Apache Commons-Fileupload library classes
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

                // parse request
                List<FileItem> items = servletFileUpload.parseRequest(request);
                System.out.println(items.size());

                for (FileItem file : items) {
                    if (file.isFormField()) {
                        imageName = file.getString();
                        if (!imageName.isEmpty()) {
                            img.setImageName(imageName);
                        }
                    } else {
                        imageSize = file.getSize() / 1024;
                        if (imageSize != 0) {
                            bytes = file.get();
                            img.setImageSize(imageSize);
                            img.setPhoto(bytes);
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Error occurred ");
            }

            if (img.getImageSize() > 1024 ||
                    (ImageSize.getImagesSize(user.getUsername()) + imageSize - currentImageSize) > 10240) {
                System.out.println("Image size exceeded");
            } else {
                imageManagement.editImage(img);
            }
            User userUpdated = login.getUserDetails(((User) request.getSession().getAttribute("user")).getUsername());
            request.getSession().setAttribute("user", userUpdated);
            response.sendRedirect("userhome.jsp");
        }
    }
}
