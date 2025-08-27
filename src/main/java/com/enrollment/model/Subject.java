package com.enrollment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Subject {
    private Long id;
    private String code;
    private String name;
    private int units;
    private int yearLevel;
    private int semester;
}
