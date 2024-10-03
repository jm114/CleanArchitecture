package com.spring.clean.lecture.domain;

import com.spring.clean.lecture.infrastructure.entity.LectureEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class Lecture {
    private long lectureId;
    private long courseId;
    private int capacity;
    private Timestamp lectureDate;  //수강일

    public static Lecture toDomain(LectureEntity entity){
        return Lecture.builder()
                .lectureId(entity.getId())
                .courseId(entity.getCourse().getId())
                .capacity(entity.getCapacity())
                .lectureDate(entity.getLectureDt())
                .build();

    }

}