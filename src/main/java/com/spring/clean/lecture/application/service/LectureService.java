package com.spring.clean.lecture.application.service;

import com.spring.clean.lecture.domain.Enrollment;
import com.spring.clean.lecture.domain.Lecture;
import com.spring.clean.lecture.domain.repository.EnrollmentRepository;
import com.spring.clean.lecture.domain.repository.LectureRepository;
import com.spring.clean.lecture.exception.EnrollmentException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final EnrollmentRepository enrollmentRepository;
    private final LectureRepository lectureRepository;

    @Transactional
    public Enrollment enroll(Enrollment applyInfo) {
        Enrollment enrolled = enrollmentRepository.save(applyInfo); //수강 신청
        int currentCapacity = lectureRepository.updateCapacity(enrolled.getLectureId());  //허용인원 변경
        if(currentCapacity==30){
            throw new  EnrollmentException("정원이 마감되었습니다.");
        }
        return enrolled;
    }

    public Enrollment isApplied(Enrollment applyInfo) {
        return enrollmentRepository.getAppliedStatus(applyInfo);
    }

    public List<Lecture> availableList(long userId) {
        return lectureRepository.getAvailableLectures(userId, LocalDate.now());
    }

    public List<Enrollment> enrolledList(long userId) {
        return enrollmentRepository.getEnrolledLectures(userId);
    }
}

