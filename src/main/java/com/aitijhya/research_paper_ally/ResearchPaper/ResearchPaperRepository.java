package com.aitijhya.research_paper_ally.ResearchPaper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ResearchPaperRepository extends JpaRepository<ResearchPaper, Long> {
    List<ResearchPaper> findByTitleIgnoreCase(String title);

    List<ResearchPaper> findByAuthorContributionsUserEmail(String email);

    @Query("SELECT p from ResearchPaper p LEFT JOIN FETCH p.sections WHERE p.id = :id")
    Optional<ResearchPaper> findByIdWithSections(@Param("id") Long id);

    List<ResearchPaper> findByCitations(Set<ResearchPaper> citations);

    List<ResearchPaper> findByCitationsId(Long id);

    List<ResearchPaper> findByReviewsRatingGreaterThanEqual(Integer rating);

    List<ResearchPaper> findByReviewsRatingGreaterThan(Integer rating);
}
