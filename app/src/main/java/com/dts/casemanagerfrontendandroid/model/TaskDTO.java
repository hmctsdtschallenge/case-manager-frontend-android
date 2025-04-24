package com.dts.casemanagerfrontendandroid.model;

import java.time.LocalDateTime;

public record TaskDTO(
        String title,
        String description,
        String status,
        LocalDateTime createdDate,
        LocalDateTime dueDate) {
}
