package com.image.backend;

import com.image.session.HibernateSession;
import com.image.models.User;
import org.hibernate.Session;


public class UserHandling  {

    
    public Boolean userAuthentication(String username, String password) {
        try (Session session = HibernateSession.getSessionInstance()) {
            session.getTransaction().begin();
            User user = session.get(User.class, username);
            if (user.getUsername() != null && user.getPassword().equals(password))
                return true;
            else
                return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    
    public User getUserDetails(String username) {
        User user = null;
        try (Session session = HibernateSession.getSessionInstance();) {
            session.getTransaction().begin();
            user = session.get(User.class, username);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    
    public void updatePassword(String username, String password) {
        User user = null;
        try (Session session = HibernateSession.getSessionInstance();) {
            session.getTransaction().begin();
            user = session.get(User.class, username);
            user.setPassword(password);
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
