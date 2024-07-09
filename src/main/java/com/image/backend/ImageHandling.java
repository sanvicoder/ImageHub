package com.image.backend;

import com.image.models.Image;
import com.image.session.HibernateSession;
import org.hibernate.Query;
import org.hibernate.Session;


public class ImageHandling {


    public void addImage(Image image) {
        try (Session session = HibernateSession.getSessionInstance();) {
            session.getTransaction().begin();
            session.save(image);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Image getImage(String imageId) {
        Image image = null;
        try (Session session = HibernateSession.getSessionInstance();) {
            session.getTransaction().begin();
            String queryString = "from Image where imageId = :imageId";
            Query query = session.createQuery(queryString).setString("imageId", imageId);

            Object queryResult = query.uniqueResult();
            return (Image) queryResult;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to connect to database");
        }
        return image;
    }

    public void editImage(Image newImage) {
        try (Session session = HibernateSession.getSessionInstance();) {
            session.beginTransaction();
            session.update(newImage);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteImage(String imageid) {
        try (Session session = HibernateSession.getSessionInstance();) {
            session.beginTransaction();

            String query = "delete from Image where imageId= :imageId";
            session.createQuery(query).setString("imageId", imageid).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
