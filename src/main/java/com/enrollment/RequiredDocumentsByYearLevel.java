package com.enrollment;

import java.util.Set;

public enum RequiredDocumentsByYearLevel {
    FIRST_YEAR(Set.of("High School Report Card", "Birth Certificate", "Good Moral Certificate")),
    SECOND_YEAR(Set.of("First Year Transcript", "Updated Registration Form")),
    THIRD_YEAR(Set.of("Second Year Transcript", "Internship Application Form")),
    FOURTH_YEAR(Set.of("Third Year Transcript", "Thesis Proposal Form"));

    private final Set<String> requiredDocuments;

    RequiredDocumentsByYearLevel(Set<String> requiredDocuments) {
        this.requiredDocuments = requiredDocuments;
    }

    public Set<String> getRequiredDocuments() {
        return requiredDocuments;
    }

    // Optional: map numeric year level (1,2,3,4) to enum
    // 👇 Normalization logic
    public static RequiredDocumentsByYearLevel fromInput(String input) {
        String normalized = input.trim().toUpperCase().replaceAll("[^A-Z0-9]", "");

        switch (normalized) {
            case "1":
            case "FIRSTYEAR":
                return FIRST_YEAR;
            case "2":
            case "SECONDYEAR":
                return SECOND_YEAR;
            case "3":
            case "THIRDYEAR":
                return THIRD_YEAR;
            case "4":
            case "FOURTHYEAR":
                return FOURTH_YEAR;
            default:
                throw new IllegalArgumentException("Invalid year level: " + input);
        }
    }
}
