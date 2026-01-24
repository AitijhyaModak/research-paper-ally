package com.aitijhya.research_paper_ally.services;

import com.aitijhya.research_paper_ally.ResearchPaper.ResearchPaper;
import com.aitijhya.research_paper_ally.ResearchPaper.ResearchPaperRepository;
import com.aitijhya.research_paper_ally.Review.Review;
import com.aitijhya.research_paper_ally.Review.ReviewRepository;
import com.aitijhya.research_paper_ally.User.User;
import com.aitijhya.research_paper_ally.User.UserRepository;
import com.aitijhya.research_paper_ally.exception.NotAuthorizedException;
import com.aitijhya.research_paper_ally.exception.ResourceNotFoundException;
import com.aitijhya.research_paper_ally.requestDTO.CreateReviewDTO;
import com.aitijhya.research_paper_ally.requestDTO.EditReviewDTO;
import com.aitijhya.research_paper_ally.responseDTO.EditReviewResponseDTO;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Data
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ResearchPaperRepository researchPaperRepository;
    private final UserRepository userRepository;

    @Transactional
    public Review createReview(CreateReviewDTO createReviewDTO) {
        ResearchPaper paper = researchPaperRepository.findById(createReviewDTO.paperId()).orElseThrow(
                () -> new ResourceNotFoundException("Resource paper does not exist.")
        );

        User user = userRepository.findById(createReviewDTO.reviewerId()).orElseThrow(
                () -> new ResourceNotFoundException("User does not exist.")
        );


        Review review = Review.builder()
                .paper(paper)
                .reviewer(user)
                .rating(createReviewDTO.rating())
                .comments(createReviewDTO.comments())
                .createdAt(LocalDateTime.now())
                .modifiedAt(null)
                .build();

        return reviewRepository.save(review);
    }

    @Transactional
    public EditReviewResponseDTO editReview(EditReviewDTO editReviewDTO) {
        Review review = reviewRepository.findById(editReviewDTO.reviewId()).orElseThrow(
                () -> new ResourceNotFoundException("Review does not exist.")
        );

        log.info(editReviewDTO.toString());

        if (!review.getReviewer().getId().equals(editReviewDTO.reviewerId()))
            throw new NotAuthorizedException("Unauthorized");

        review.setComments(editReviewDTO.newComment());
        review.setRating(editReviewDTO.newRating());
        review.setModifiedAt(LocalDateTime.now());

        return new EditReviewResponseDTO(
                review.getId(),
                review.getReviewer().getId(),
                review.getPaper().getId(),
                review.getRating(),
                review.getComments(),
                review.getCreatedAt(),
                review.getModifiedAt()
        );
    }
}