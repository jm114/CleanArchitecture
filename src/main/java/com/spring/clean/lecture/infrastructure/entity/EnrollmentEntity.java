package com.spring.clean.lecture.infrastructure.entity;

import com.spring.clean.lecture.domain.Enrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
    private Timestamp applyTime;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private LectureEntity lecture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    public static EnrollmentEntity toEntity(Enrollment enrollInfo) {
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(enrollInfo.getLectureId());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(enrollInfo.getUserId());

        return EnrollmentEntity.builder()
                .lecture(lectureEntity)
                .user(userEntity)
                .applyTime(enrollInfo.getApplyTime())
                .build();
    }

}
