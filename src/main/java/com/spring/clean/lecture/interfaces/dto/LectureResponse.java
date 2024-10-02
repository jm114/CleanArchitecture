package com.spring.clean.lecture.interfaces.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureResponse {
    private long userId;
    private long lectureId;
    private String status;
}
