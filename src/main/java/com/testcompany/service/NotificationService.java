package com.testcompany.service;

import com.testcompany.dao.NotificationDao;
import com.testcompany.entity.Book;
import com.testcompany.entity.Notification;

import javax.persistence.Persistence;
import java.util.List;

public class NotificationService {
    private NotificationDao notificationDao;

    public NotificationService() {
        try {
            notificationDao = new NotificationDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public List<Notification> getAllNotifications() {
        return notificationDao.findAll();
    }

    public void addNotification (Notification newNotification) {
        notificationDao.create(newNotification);
    }

    public void deleteNotification (Notification notification, int id) {
        notificationDao.remove(notification,id);
    }

    public List<Notification> getNotificationsForBook(Book inputBook){
        return notificationDao.findByBook(inputBook);
    }

    public List<Notification> getNotificationsForUsername(String username){
        return notificationDao.findByUsername(username);
    }

}
