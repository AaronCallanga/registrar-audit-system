package com.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student {
    private Long id;
    private String studentNumber;
    private String name;
    private String yearLevel;
    private String program;
    private String contact;

    //private List<RequiredDocument>;
    //every time admin enroll a student, it should also insert required documents
    // to the database w/ particular status status (Submitted, Missing, Pending Verification)
}
