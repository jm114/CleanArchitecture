package com.spring.clean.lecture.domain;

import com.spring.clean.lecture.infrastructure.entity.EnrollmentEntity;
import com.spring.clean.lecture.interfaces.dto.LectureRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
public class Enrollment {
    private long enrollId;
    private long lectureId;
    private long userId;
    private String title;
    private String teacher;
    private Timestamp applyTime;

    public static Enrollment toDomain(EnrollmentEntity entity){
        return Enrollment.builder()
                .enrollId(entity.getId())
                .lectureId(entity.getLecture().getId())
                .userId(entity.getUser().getId())
                .applyTime(entity.getApplyTime())
                .build();
    }

    public static Enrollment toDomain(LectureRequest request){
        return Enrollment.builder()
                .lectureId(request.getLectureId())
                .userId(request.getUserId())
                .applyTime(request.getApplyTime())
                .build();
    }

    public static Enrollment toDomainWithCourse(EnrollmentEntity entity){
        return Enrollment.builder()
                .enrollId(entity.getId())
                .lectureId(entity.getLecture().getId())
                .userId(entity.getUser().getId())
                .applyTime(entity.getApplyTime())
                .title(entity.getLecture().getCourse().getTitle())
                .teacher(entity.getLecture().getCourse().getTeacher())
                .build();
    }

}
