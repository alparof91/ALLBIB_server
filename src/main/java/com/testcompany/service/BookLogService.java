package com.testcompany.service;

import com.testcompany.dao.BookLogDao;
import com.testcompany.entity.Book;
import com.testcompany.entity.BookLog;
import com.testcompany.entity.Review;

import javax.persistence.Persistence;
import java.util.List;

public class BookLogService {
    private BookLogDao bookLogDao;

    public BookLogService() {
        try {
            bookLogDao = new BookLogDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public List<BookLog> getAllBookLogs() {
        return bookLogDao.findAll();
    }

    public List<BookLog> getBookLogsForBook(Book inputBook){
        return bookLogDao.findByBook(inputBook);
    }

    public void addBookLog (BookLog newBookLog) {
        bookLogDao.create(newBookLog);
    }

    public void deleteBookLog (BookLog bookLog, int id) { bookLogDao.remove(bookLog,id); }
}
