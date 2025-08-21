package com.enrollment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RequiredDocument { // document required for enrollment
    private Long id;
    private Long studentId;
    private String documentType;
    private String status;      // SUBMITTED, MISSING, PENDING VERIFICATION(optional)
    private LocalDateTime submittedDate;
}
