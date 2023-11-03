package com.locationbasedfoodieservice.review.repository;

import com.locationbasedfoodieservice.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {


}
