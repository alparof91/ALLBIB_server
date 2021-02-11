package com.testcompany.service;

import com.testcompany.dao.ReviewDao;
import com.testcompany.entity.Book;
import com.testcompany.entity.Review;

import javax.persistence.Persistence;
import java.util.List;

public class ReviewService {
    private ReviewDao reviewDao;

    public ReviewService() {
        try {
            reviewDao = new ReviewDao(Persistence.createEntityManagerFactory("ALLBIB"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public List<Review> getAllReviews() {
        return reviewDao.findAll();
    }

    public List<Review> getReviewsForBook(Book inputBook){
        return reviewDao.findByBook(inputBook);
    }

    public void addReview (Review newReview) {
        reviewDao.create(newReview);
    }
}
