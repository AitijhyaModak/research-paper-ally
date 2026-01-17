package com.aitijhya.research_paper_ally.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aitijhya.research_paper_ally.DTO.ResearchPaperDTO;
import com.aitijhya.research_paper_ally.ResearchPaper.ResearchPaper;
import com.aitijhya.research_paper_ally.services.ResearchPaperService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ResearchPaperController {
    private final ResearchPaperService researchPaperService;

    @PostMapping("/save-paper") 
    public ResponseEntity<ResearchPaper> savePaper(@RequestBody ResearchPaperDTO paperDTO) {
        ResearchPaper savedPaper = researchPaperService.savePaper(paperDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPaper);
    }
}
