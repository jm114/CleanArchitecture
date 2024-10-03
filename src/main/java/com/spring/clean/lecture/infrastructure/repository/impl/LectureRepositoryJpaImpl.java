package com.spring.clean.lecture.infrastructure.repository.impl;

import com.spring.clean.lecture.domain.Lecture;
import com.spring.clean.lecture.domain.repository.LectureRepository;
import com.spring.clean.lecture.infrastructure.entity.LectureEntity;
import com.spring.clean.lecture.infrastructure.repository.LectureJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LectureRepositoryJpaImpl implements LectureRepository {

    private final LectureJpaRepository jpaRepository;

    public LectureRepositoryJpaImpl(LectureJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int updateCapacity(Long lectureId) {
        LectureEntity lectureEntity = jpaRepository.findById(lectureId)
                .orElseThrow(() -> new EntityNotFoundException("특강이 존재하지 않습니다."));

        lectureEntity.setCapacity(lectureEntity.getCapacity() + 1);

        return lectureEntity.getCapacity();
    }

    @Override
    public List<Lecture> getAvailableLectures(Long userId, LocalDate today) {
        String jpql = "SELECT L FROM Lecture L WHERE NOT EXISTS (" +
                        "SELECT 1 FROM Enrollment E WHERE L.id = E.lectureId " +
                        "AND E.userId = :userId " +
                        "AND E.capacity = 0 " +
                        "and L.lecture_dt <> :today)";

        return entityManager.createQuery(jpql, LectureEntity.class)
                .setParameter("userId", userId)
                .setParameter("today", today)
                .getResultList()
                .stream()
                .map(Lecture::toDomain)
                .collect(Collectors.toList());
    }


}
