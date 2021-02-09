package com.testcompany.service;

import com.testcompany.dao.ReaderDao;
import com.testcompany.entity.Readers;

import javax.persistence.Persistence;
import java.util.List;

public class ReaderService {
    private ReaderDao readerDao;

    public ReaderService() {
        try {
            readerDao = new ReaderDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addReader (Readers newReader) {
        readerDao.create(newReader);
    }

    public void updateReader (Readers updatedReader) {
        readerDao.update(updatedReader);
    }

    public List<Readers> findReaderByFirstName(String firstName) {
        return readerDao.findByFirstName(firstName);
    }

    public List<Readers> findReaderBySecondName(String secondName) {
        return readerDao.findBySecondName(secondName);
    }

    public List<Readers> findReaderByPhone(String phone) {
        return readerDao.findByPhone(phone);
    }

    public List<Readers> getAllReaders() {
        return readerDao.findAll();
    }
}
