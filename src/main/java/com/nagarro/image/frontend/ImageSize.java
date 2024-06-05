package com.nagarro.image.frontend;

import java.util.Collection;

import com.nagarro.image.backend.UserHandling;
import com.nagarro.image.models.Image;
import com.nagarro.image.models.User;

public class ImageSize {

    private static double totalSize = 0;
    private static UserHandling UserHandling = new UserHandling();
    private static User user;
    private static Collection<Image> images;


    public static double getImagesSize(String username) {
        user = UserHandling.getUserDetails(username);
        images = user.getImages();
        for (Image image : images) {
            totalSize += image.getImageSize();
        }
        return totalSize;
    }
}
