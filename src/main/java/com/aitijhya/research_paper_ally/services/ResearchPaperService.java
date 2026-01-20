package com.aitijhya.research_paper_ally.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aitijhya.research_paper_ally.AuthorContribution.AuthorContribution;
import com.aitijhya.research_paper_ally.ResearchPaper.ResearchPaper;
import com.aitijhya.research_paper_ally.ResearchPaper.ResearchPaperRepository;
import com.aitijhya.research_paper_ally.Section.Section;
import com.aitijhya.research_paper_ally.User.User;
import com.aitijhya.research_paper_ally.User.UserRepository;
import com.aitijhya.research_paper_ally.exception.ResourceNotFoundException;
import com.aitijhya.research_paper_ally.requestDTO.ResearchPaperDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResearchPaperService {
    private final ResearchPaperRepository researchPaperRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResearchPaper uploadPaper(ResearchPaperDTO paperDTO) {
        ResearchPaper paper = ResearchPaper.builder()
                .title(paperDTO.title())
                .abstractText(paperDTO.abstractText())
                .uuid(UUID.randomUUID().toString())
                .build();

        List<AuthorContribution> authorContributions = paperDTO.authorContributions().stream().map(
                obj -> {
                    User user = userRepository.findByEmail(obj.userEmail()).orElseThrow(
                            () -> new ResourceNotFoundException("Author does not exist"));
                    return AuthorContribution.builder()
                            .user(user)
                            .contributionPercentage(obj.contributionPercentage())
                            .paper(paper)
                            .build();
                }).toList();

        Set<ResearchPaper> citations = paperDTO.citationIds().stream().map(
                obj -> {
                    ResearchPaper citedPaper = researchPaperRepository.findById(obj)
                            .orElseThrow(() -> new ResourceNotFoundException("Cited paper does not exist."));

                    return citedPaper;
                }).collect(Collectors.toSet());

        List<Section> sections = paperDTO.sections().stream().map(
                obj -> Section.builder()
                        .title(obj.title())
                        .content(obj.content())
                        .paper(paper)
                        .build())
                .toList();

        paper.setCitations(citations);
        paper.setSections(sections);
        paper.setAuthorContributions(authorContributions);

        return researchPaperRepository.save(paper);
    }
}
