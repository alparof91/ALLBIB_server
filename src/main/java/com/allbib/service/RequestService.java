package com.allbib.service;

import com.allbib.dao.RequestDao;
import com.allbib.entity.Request;

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
        System.out.println("Trying to add new Request");
        requestDao.create(newRequest);
    }

    public void deleteRequest (Request request, int id) { requestDao.remove(request,id); }

//    public List<Request> getRequestsForBook(Book inputBook){
//        return requestDao.findByBook(inputBook);
//    }


}
