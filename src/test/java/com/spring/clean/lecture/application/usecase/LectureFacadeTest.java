package com.spring.clean.lecture.application.usecase;

import com.spring.clean.lecture.domain.Enrollment;
import com.spring.clean.lecture.exception.EnrollmentException;
import com.spring.clean.lecture.infrastructure.repository.EnrollmentJpaRepository;
import com.spring.clean.lecture.infrastructure.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class LectureFacadeTest {

    @Autowired
    private LectureFacade lectureFacade;

    @Autowired
    private EnrollmentJpaRepository enrollmentJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    void setUp() {
    }



    @Test
    void 정원초과_테스트() throws InterruptedException {
        int totalApplicants = 40;
        List<Enrollment> enrollments = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(totalApplicants);
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        for (int i = 0; i < totalApplicants; i++) {
            Enrollment enrollment = Enrollment.builder()
                    .lectureId(1L)
                    .userId(i+10)
                    .applyTime(LocalDateTime.now())
                    .build();

            executorService.submit(() -> {
                try {
                    Enrollment result = lectureFacade.applyLecture(enrollment);
                    enrollments.add(result); // 성공한 신청만 추가
                } catch (EnrollmentException e) {
                    // 실패
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        // 성공한 신청자는 30명, 실패한 신청자는 10명이어야 함
        assertThat(enrollments.size()).isEqualTo(30);
        assertThat(enrollmentJpaRepository.count()).isEqualTo(30); // DB에서도 확인
    }


    @Test
    void 사용자별_중복신청_금지_테스트() throws InterruptedException {
        int totalApplicants = 5;
        List<Enrollment> enrolls = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(totalApplicants);
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        for (int i = 0; i < totalApplicants; i++) {
            Enrollment enrollment = Enrollment.builder()
                    .lectureId(2L)
                    .userId(1145L)
                    .applyTime(LocalDateTime.now())
                    .build();

            executorService.submit(() -> {
                try {
                    Enrollment result = lectureFacade.applyLecture(enrollment);
                    if (result != null) {
                        enrolls.add(result);
                    }
                } catch (EnrollmentException e) {
                    // 실패한 신청은 무시
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        assertThat(enrolls.size()).isEqualTo(1);
        assertThat(enrollmentJpaRepository.count()).isEqualTo(1);
    }
}