package com.spring.clean.lecture.infrastructure.entity;

import com.spring.clean.lecture.domain.Enrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enrollment")
@Builder
@Entity
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime applyTime;

    @Column(nullable = false)
    private long lectureId;

    @Column(nullable = false)
    private long userId;


    public static EnrollmentEntity toEntity(Enrollment enrollInfo) {

        return EnrollmentEntity.builder()
                .lectureId(enrollInfo.getLectureId())
                .userId(enrollInfo.getUserId())
                .applyTime(enrollInfo.getApplyTime())
                .build();
    }

}
