package com.allbib.service;

import com.allbib.dao.GivenBookDao;
import com.allbib.entity.Book;
import com.allbib.entity.GivenBook;

import javax.persistence.Persistence;
import java.util.List;

public class GivenBookService {
    private GivenBookDao givenBookDao;

    public GivenBookService() {
        try {
            givenBookDao = new GivenBookDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public List<GivenBook> getAllGivenBooks() {
        return givenBookDao.findAll();
    }

    public List<GivenBook> getGivenBooksForBook(Book inputBook){
        return givenBookDao.findByBook(inputBook);
    }

    public void addGivenBook (GivenBook newGivenBook) {
        givenBookDao.create(newGivenBook);
    }

    public void deleteGivenBook (GivenBook givenBook, int id) { givenBookDao.remove(givenBook,id); }
}
