package com.testcompany.service;

import com.testcompany.dao.BooksDao;
import com.testcompany.entity.Books;

import javax.persistence.Persistence;
import java.util.List;

public class BooksService {
    private BooksDao booksDao;

    public BooksService() {
        try {
            booksDao = new BooksDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addBook (Books newBook) {
        booksDao.create(newBook);
    }

    public void deleteBook (int ID) { booksDao.remove(1); }

    public void updateBook (Books updatedBook) {
        booksDao.update(updatedBook);
    }

    public List<Books> findBookByTitle(String title) {
        return booksDao.findByTitle(title);
    }

    public List<Books> findBookByAuthor(String author) {
        return booksDao.findByTitle(author);
    }

    public List<Books> findBookByPublisher(String publisher) {
        return booksDao.findByTitle(publisher);
    }

    public List<Books> getAllBooks() {
        return booksDao.findAll();
    }
}
