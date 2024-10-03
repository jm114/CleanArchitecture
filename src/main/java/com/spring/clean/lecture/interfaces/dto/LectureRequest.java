package com.spring.clean.lecture.interfaces.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class LectureRequest {
    private long userId;
    private long lectureId;
    private Timestamp applyTime;
}
