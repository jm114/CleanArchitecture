package com.spring.clean.lecture.infrastructure.repository;

import com.spring.clean.lecture.infrastructure.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentJpaRepository extends JpaRepository<EnrollmentEntity, Long> {

    List<EnrollmentEntity> findByUserId(Long userId);

    EnrollmentEntity findByUserIdAndLectureId(long userId, long lectureId);
}
