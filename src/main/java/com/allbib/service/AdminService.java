package com.allbib.service;

import com.allbib.dao.AdminDao;
import com.allbib.entity.Admin;

import javax.persistence.Persistence;
import java.util.List;

public class AdminService {
    private AdminDao adminDao;

    public AdminService() {
        try {
            adminDao = new AdminDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addAdmin (Admin newAdmin) {
        adminDao.create(newAdmin);
    }

    public void updateAdmin (Admin updatedAdmin) {
        adminDao.update(updatedAdmin);
    }

    public List<Admin> findAdminByFirstName(String firstName) {
        return adminDao.findByFirstName(firstName);
    }

    public List<Admin> findAdminBySecondName(String secondName) {
        return adminDao.findBySecondName(secondName);
    }

    public List<Admin> findAdminByPhone(String phone) {
        return adminDao.findByPhone(phone);
    }

    public List<Admin> getAllAdmins() {
        return adminDao.findAll();
    }
}
