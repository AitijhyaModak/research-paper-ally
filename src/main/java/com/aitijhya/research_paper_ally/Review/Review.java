package com.aitijhya.research_paper_ally.Review;

import java.time.LocalDateTime;

import com.aitijhya.research_paper_ally.ResearchPaper.ResearchPaper;
import com.aitijhya.research_paper_ally.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
   private User reviewer;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "paper_id")
   @JsonBackReference
   private ResearchPaper paper;

   private Integer rating;

   private String comments;

   private LocalDateTime createdAt;

   private LocalDateTime modifiedAt;
}
