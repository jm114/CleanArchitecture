package com.spring.clean.lecture.domain;

import com.spring.clean.lecture.infrastructure.entity.LectureEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Lecture {
    private long lectureId;
    private long courseId;
    private int capacity;
    private LocalDateTime lectureDt;  //수강일

    public static Lecture toDomain(LectureEntity entity){
        return Lecture.builder()
                .lectureId(entity.getId())
                .courseId(entity.getCourseId())
                .capacity(entity.getCapacity())
                .lectureDt(entity.getLectureDt())
                .build();

    }

    @Override
    public String toString() {
        return "Lecture{" +
                "lectureId=" + lectureId +
                ", courseId=" + courseId +
                ", capacity=" + capacity +
                ", lectureDate=" + lectureDt +
                '}';
    }

}