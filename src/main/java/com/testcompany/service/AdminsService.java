package com.testcompany.service;

import com.testcompany.dao.AdminsDao;
import com.testcompany.entity.Admins;

import javax.persistence.Persistence;
import java.util.List;

public class AdminsService {
    private AdminsDao adminsDao;

    public AdminsService() {
        try {
            adminsDao = new AdminsDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addAdmin (Admins newAdmin) {
        adminsDao.create(newAdmin);
    }

    public void updateAdmin (Admins updatedAdmin) {
        adminsDao.update(updatedAdmin);
    }

    public List<Admins> findAdminByFirstName(String firstName) {
        return adminsDao.findByFirstName(firstName);
    }

    public List<Admins> findAdminBySecondName(String secondName) {
        return adminsDao.findBySecondName(secondName);
    }

    public List<Admins> findAdminByPhone(String phone) {
        return adminsDao.findByPhone(phone);
    }

    public List<Admins> getAllAdmins() {
        return adminsDao.findAll();
    }
}
