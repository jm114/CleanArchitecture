package com.spring.clean.lecture.domain.repository;

import com.spring.clean.lecture.domain.Enrollment;
import com.spring.clean.lecture.domain.Lecture;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LectureRepository {
// 여기까지 서비스 고유 영역(jpa 따위가 상관할 곳이 아님)

    //1.제한 인원 수정하기
    int updateCapacity(Long lecutreId);

    //2. userId로 신청가능한 특강 조회
    List<Lecture> getAvailableLectures(Enrollment applyInfo);

    List<Lecture> getAvailableLecturesByUser(Enrollment applyInfo);
}
