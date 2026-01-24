package com.aitijhya.research_paper_ally.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aitijhya.research_paper_ally.ResearchPaper.ResearchPaper;
import com.aitijhya.research_paper_ally.requestDTO.ResearchPaperDTO;
import com.aitijhya.research_paper_ally.services.ResearchPaperService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/research-paper")
@RequiredArgsConstructor
public class ResearchPaperController {
   private final ResearchPaperService researchPaperService;

   @PostMapping("/upload")
   public ResponseEntity<ResearchPaper> uploadPaper(@RequestBody @Valid ResearchPaperDTO paperDTO) {
      return ResponseEntity.status(HttpStatus.CREATED).body(researchPaperService.uploadPaper(paperDTO));
   }
}
