package com.spring.clean.lecture.interfaces.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureRequest {
    private long userId;
    private long lectureId;
    private LocalDateTime applyTime;
}
