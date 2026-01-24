package com.aitijhya.research_paper_ally.controllers;

import com.aitijhya.research_paper_ally.Review.Review;
import com.aitijhya.research_paper_ally.requestDTO.CreateReviewDTO;
import com.aitijhya.research_paper_ally.requestDTO.EditReviewDTO;
import com.aitijhya.research_paper_ally.responseDTO.EditReviewResponseDTO;
import com.aitijhya.research_paper_ally.services.ReviewService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@Data
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody @Valid CreateReviewDTO createReviewDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(createReviewDTO));
    }

    @PostMapping("/edit")
    public ResponseEntity<EditReviewResponseDTO> editReview(@RequestBody @Valid EditReviewDTO editReviewDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.editReview(editReviewDTO));
    }
}
