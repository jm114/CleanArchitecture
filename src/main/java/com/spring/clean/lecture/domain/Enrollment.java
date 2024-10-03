package com.spring.clean.lecture.domain;

import com.spring.clean.lecture.infrastructure.entity.CourseEntity;
import com.spring.clean.lecture.infrastructure.entity.EnrollmentEntity;
import com.spring.clean.lecture.interfaces.dto.LectureRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Enrollment {
    private long enrollId;
    private long lectureId;
    private long userId;
    private LocalDateTime applyTime;

    public static Enrollment toDomain(EnrollmentEntity entity){
        return Enrollment.builder()
                .enrollId(entity.getId())
                .lectureId(entity.getLectureId())
                .userId(entity.getUserId())
                .applyTime(entity.getApplyTime())
                .build();
    }

    public static Enrollment toDomain(LectureRequest request){
        return Enrollment.builder()
                .lectureId(request.getLectureId())
                .userId(request.getUserId())
                .applyTime(request.getApplyTime().toLocalDateTime())
                .build();
    }


}
