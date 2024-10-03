package com.spring.clean.lecture.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lecture")
@Builder
@Entity
public class LectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Timestamp lectureDt;

    @Column(nullable = false)
    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY)  // 강좌와 특강은 다대일 관계
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @OneToMany(mappedBy = "lecture")
    private List<EnrollmentEntity> enrollments;

}
