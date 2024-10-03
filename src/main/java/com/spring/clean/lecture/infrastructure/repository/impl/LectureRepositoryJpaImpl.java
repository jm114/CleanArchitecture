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
import java.time.LocalDateTime;
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
    public List<Lecture> getAvailableLectures(Long userId, LocalDateTime today) {
        String jpql = "SELECT L FROM LectureEntity L " +
                        "WHERE NOT EXISTS (SELECT 1 FROM EnrollmentEntity E " +
                                                    "WHERE (E.lectureId = L.id AND E.userId = :userId) " +
                                                    "OR L.capacity = 30 " +
                                                    "OR L.lectureDt < :today)";

        List<LectureEntity> result = entityManager.createQuery(jpql, LectureEntity.class)
                .setParameter("userId", userId)
                .setParameter("today", today)
                .getResultList();

        // 결과를 콘솔에 출력
        System.out.println("Available Lectures: " + result);

        return result.stream()
                .map(Lecture::toDomain)
                .collect(Collectors.toList());
    }


}
