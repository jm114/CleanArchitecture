package com.spring.clean.lecture.infrastructure.repository;

import com.spring.clean.lecture.infrastructure.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {
}
