package com.testcompany.service;

import com.testcompany.dao.RequestDao;
import com.testcompany.entity.Request;

import javax.persistence.Persistence;
import java.util.List;

public class RequestService {
    private RequestDao requestDao;

    public RequestService() {
        try {
            requestDao = new RequestDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public List<Request> getAllRequests() {
        return requestDao.findAll();
    }

    public void addRequest (Request newRequest) {
        requestDao.create(newRequest);
    }

    public void deleteRequest (Request request, int id) { requestDao.remove(request,id); }

//    public List<Request> getRequestsForBook(Book inputBook){
//        return requestDao.findByBook(inputBook);
//    }


}
