package com.aitijhya.research_paper_ally.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aitijhya.research_paper_ally.AuthorContribution.AuthorContribution;
import com.aitijhya.research_paper_ally.DTO.AuthorContributionDTO;
import com.aitijhya.research_paper_ally.DTO.ResearchPaperDTO;
import com.aitijhya.research_paper_ally.DTO.SectionDTO;
import com.aitijhya.research_paper_ally.ResearchPaper.ResearchPaper;
import com.aitijhya.research_paper_ally.ResearchPaper.ResearchPaperRepository;
import com.aitijhya.research_paper_ally.Section.Section;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResearchPaperService {
    private final ResearchPaperRepository researchPaperRepository;

    @Transactional
    public ResearchPaper savePaper(ResearchPaperDTO paperDTO) {
        String title = paperDTO.title();
        String abstractText = paperDTO.abstractText();
        List<Long> citationIds = paperDTO.citationIds();
        List<SectionDTO> sectionDTOs = paperDTO.sections();
        List<AuthorContributionDTO> contributionDTOs = paperDTO.authorContributions();

        List<Section> sections = sectionDTOs.stream()
            .map(obj -> Section.builder().title(obj.title()).content(obj.content()).build())
            .toList();

        List<AuthorContribution> contributions = contributionDTOs.stream()
            .map(obj -> AuthorContribution.builder().contributionPercentage(obj.contributionPercentage()).build())
            .toList();
        
        Set<ResearchPaper> citations = new HashSet<>(researchPaperRepository.findAllById(citationIds));


        ResearchPaper paper = ResearchPaper.builder()
            .title(title)
            .abstractText(abstractText)
            .uuid(UUID.randomUUID().toString())
            .citations(citations)
            .sections(sections)
            .authorContributions(contributions)
            .build();
        
        return researchPaperRepository.save(paper);
    }
}
