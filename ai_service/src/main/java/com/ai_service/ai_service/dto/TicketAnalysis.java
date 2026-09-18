package com.ai_service.ai_service.dto;

public record TicketAnalysis(
        String category,       // BILLING, TECHNICAL, ACCOUNT, GENERAL, BUG_REPORT
        String sentiment,      // POSITIVE, NEUTRAL, NEGATIVE
        String suggestedReply
) {}