package com.spring.clean.lecture.infrastructure.repository.impl;

import com.spring.clean.lecture.domain.Enrollment;
import com.spring.clean.lecture.domain.repository.EnrollmentRepository;
import com.spring.clean.lecture.infrastructure.entity.EnrollmentEntity;
import com.spring.clean.lecture.infrastructure.repository.EnrollmentJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EnrollmentRepositoryJpaImpl implements EnrollmentRepository {

    private final EnrollmentJpaRepository jpaRepository;

    public EnrollmentRepositoryJpaImpl(EnrollmentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Enrollment save(Enrollment enrollInfo) {
        EnrollmentEntity enrolledLectureId = jpaRepository.save(EnrollmentEntity.toEntity(enrollInfo));
        return Enrollment.toDomain(enrolledLectureId);
    }

    @Override
    public List<Enrollment> getEnrolledLectures(Long userId) {
        List<EnrollmentEntity> result = jpaRepository.findByUserId(userId);
        return result.stream()
                .map(Enrollment::toDomainWithCourse)
                .collect(Collectors.toList());
    }

    @Override
    public Enrollment getAppliedStatus(Enrollment applyInfo) {
        EnrollmentEntity result = jpaRepository.findByUserIdAndLectureId(applyInfo.getUserId(), applyInfo.getLectureId());
        return result != null ? Enrollment.toDomain(result):null;
    }

}
