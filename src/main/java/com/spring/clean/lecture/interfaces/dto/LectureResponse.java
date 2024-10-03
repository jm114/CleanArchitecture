package com.spring.clean.lecture.interfaces.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LectureResponse {
    private long userId;
    private long lectureId;
    private String status;
}
