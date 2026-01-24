package com.aitijhya.research_paper_ally.error;

public record ErrorJson(
      int status,
      String errorMessage,
      long timestamp) {
}
