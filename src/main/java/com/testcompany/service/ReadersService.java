package com.testcompany.service;

import com.testcompany.dao.ReadersDao;
import com.testcompany.entity.Readers;

import javax.persistence.Persistence;
import java.util.List;

public class ReadersService {
    private ReadersDao readersDao;

    public ReadersService() {
        try {
            readersDao = new ReadersDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addReader (Readers newReader) {
        readersDao.create(newReader);
    }

    public void updateReader (Readers updatedReader) {
        readersDao.update(updatedReader);
    }

    public List<Readers> findReaderByFirstName(String firstName) {
        return readersDao.findByFirstName(firstName);
    }

    public List<Readers> findReaderBySecondName(String secondName) {
        return readersDao.findBySecondName(secondName);
    }

    public List<Readers> findReaderByPhone(String phone) {
        return readersDao.findByPhone(phone);
    }

    public List<Readers> getAllReaders() {
        return readersDao.findAll();
    }
}
