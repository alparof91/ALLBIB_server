package com.allbib.service;

import com.allbib.dao.BookDao;
import com.allbib.entity.Book;

import javax.persistence.Persistence;
import java.util.List;

public class BookService {
    private BookDao bookDao;

    public BookService() {
        try {
            bookDao = new BookDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addBook (Book newBook) {
        bookDao.create(newBook);
    }

    public void deleteBook (Book bookToRemove, int id) { bookDao.remove(bookToRemove,id); }

    public void updateBook (Book updatedBook) {
        bookDao.update(updatedBook);
    }

    public List<Book> findBookByTitle(String title) {
        return bookDao.findByTitle(title);
    }

    public List<Book> findBookByAuthor(String author) {
        return bookDao.findByAuthor(author);
    }

    public List<Book> findBookByPublisher(String publisher) {
        return bookDao.findByPublisher(publisher);
    }

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }
}
