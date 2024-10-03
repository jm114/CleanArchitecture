package com.spring.clean.lecture.domain.repository;

import com.spring.clean.lecture.domain.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository {
    //1. 특강 신청 정보 저장
    Enrollment save(Enrollment enrollInfo);

    //2. userId별 신청 완료한 특강 조회
    List<Enrollment> getEnrolledLectures(Long userId);

    //3. userId, lectureId로 신청 가능한 특강인지 여부 조회
    Enrollment getAppliedStatus(Enrollment applyInfo);
}
