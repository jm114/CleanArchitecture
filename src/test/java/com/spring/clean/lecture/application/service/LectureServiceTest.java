package com.spring.clean.lecture.application.service;

import com.spring.clean.lecture.domain.Enrollment;
import com.spring.clean.lecture.domain.Lecture;
import com.spring.clean.lecture.domain.repository.EnrollmentRepository;
import com.spring.clean.lecture.domain.repository.LectureRepository;
import com.spring.clean.lecture.exception.EnrollmentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LectureServiceTest {
    @InjectMocks
    private LectureService lectureService;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private LectureRepository lectureRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("수강신청성공")
    @Test
    void test1() {
        long userId = 1L;
        long lectureId = 1L;
        Enrollment enrollment = Enrollment.builder()
                .enrollId(1L)
                .lectureId(lectureId)
                .userId(userId)
                .applyTime(Timestamp.valueOf(LocalDate.now().atStartOfDay()))
                .build();

        when(lectureRepository.updateCapacity(lectureId)).thenReturn(29); // 30명 중 29명 신청된 상태
        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);
        when(enrollmentRepository.getAppliedStatus(enrollment)).thenReturn(null); // 아직 신청하지 않은 상태

        Enrollment result = lectureService.enroll(enrollment);

        assertNotNull(result);
        assertEquals(enrollment, result);
        assertEquals(enrollment.getEnrollId(), result.getEnrollId());
        assertEquals(enrollment.getLectureId(), result.getLectureId());
        assertEquals(enrollment.getUserId(), result.getUserId());
        assertEquals(enrollment.getApplyTime(), result.getApplyTime());
        verify(lectureRepository).updateCapacity(lectureId);
    }

    @DisplayName("이미 신청한 강의는 수강신청 실패")
    @Test
    void test2() {
        long userId = 1L;
        long lectureId = 1L;
        Enrollment enrollment = Enrollment.builder()
                .enrollId(1L)
                .lectureId(lectureId)
                .userId(userId)
                .applyTime(Timestamp.valueOf(LocalDate.now().atStartOfDay()))
                .build();

        when(enrollmentRepository.getAppliedStatus(enrollment)).thenReturn(enrollment);

        EnrollmentException thrown = assertThrows(EnrollmentException.class, () -> {
            lectureService.enroll(enrollment);
        });
        assertEquals("이미 신청한 강의입니다.", thrown.getMessage());
    }

    @DisplayName("30명 정원 마감된 수강신청 실패")
    @Test
    void test3() {
        long userId = 1L;
        long lectureId = 1L;
        Enrollment enrollment = Enrollment.builder()
                .enrollId(1L)
                .lectureId(lectureId)
                .userId(userId)
                .applyTime(Timestamp.valueOf(LocalDate.now().atStartOfDay()))
                .build();

        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);
        when(lectureRepository.updateCapacity(enrollment.getLectureId())).thenReturn(30); // 허용 인원 마감
        when(enrollmentRepository.getAppliedStatus(enrollment)).thenReturn(null); // 아직 신청하지 않은 상태

        EnrollmentException thrown = assertThrows(EnrollmentException.class, () -> {
            lectureService.enroll(enrollment);
        });
        assertEquals("정원이 마감되었습니다.", thrown.getMessage());
    }

    @DisplayName("수강신청 상태 조회")
    @Test
    void test4() {
        Enrollment enrollment = Enrollment.builder()
                .enrollId(1L)
                .lectureId(1L)
                .userId(1L)
                .applyTime(Timestamp.valueOf(LocalDate.now().atStartOfDay()))
                .build();

        when(enrollmentRepository.getAppliedStatus(enrollment)).thenReturn(enrollment);

        Enrollment result = lectureService.isApplied(enrollment);

        assertNotNull(result);
        assertEquals(enrollment, result);
    }

    @DisplayName("신청 가능한 특강 조회")
    @Test
    void test5() {
        long userId = 1L;
        Lecture lecture = Lecture.builder()
                .lectureId(1L)
                .courseId(1L)
                .capacity(30)
                .lectureDate(Timestamp.valueOf(LocalDate.now().atStartOfDay()))
                .build();

        when(lectureRepository.getAvailableLectures(userId, LocalDate.now())).thenReturn(Collections.singletonList(lecture));

        List<Lecture> result = lectureService.availableList(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(lecture.getLectureId(), result.get(0).getLectureId());
        assertEquals(lecture.getCapacity(), result.get(0).getCapacity());
    }

    @DisplayName("신청 완료한 특강 조회")
    @Test
    void test6() {
        long userId = 1L;
        Enrollment enrollment = Enrollment.builder()
                .enrollId(1L)
                .lectureId(1L)
                .userId(userId)
                .applyTime(Timestamp.valueOf(LocalDate.now().atStartOfDay()))
                .build();

        when(enrollmentRepository.getEnrolledLectures(userId)).thenReturn(Collections.singletonList(enrollment));

        List<Enrollment> result = lectureService.enrolledList(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(enrollment.getEnrollId(), result.get(0).getEnrollId());
        assertEquals(enrollment.getLectureId(), result.get(0).getLectureId());
    }
}