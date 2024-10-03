package com.spring.clean.lecture.infrastructure.repository;

import com.spring.clean.lecture.infrastructure.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseJpaRepository extends JpaRepository<CourseEntity, Long> {
}
