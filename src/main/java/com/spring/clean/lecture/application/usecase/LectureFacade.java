package com.spring.clean.lecture.application.usecase;

import com.spring.clean.lecture.domain.Enrollment;
import com.spring.clean.lecture.domain.Lecture;
import com.spring.clean.lecture.application.service.LectureService;
import com.spring.clean.lecture.exception.NoAvailableLecturesException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureFacade {

    private final LectureService lectureService;

    /**
     * 특강 신청
     */
    public Enrollment applyLecture(Enrollment applyInfo) {

        //신청 가능한지 체크. 신청가능한 목록 조회
        List<Lecture> list = lectureService.availableList(applyInfo.getUserId());

        if(list.isEmpty()){
            throw new NoAvailableLecturesException("신청가능한 특강이 없습니다.");
        }

        //신청가능한 특강이 있으면 신청
        Enrollment enrolled = lectureService.enroll(applyInfo);
        return enrolled;
    }

    /**
     * 특강 신청 여부 조회
     */
    public String checkLecture (Enrollment applyInfo) {
        Enrollment result = lectureService.isApplied(applyInfo);
        String message = result != null ? "신청 가능" : "신청 불가";
        return message;
    }

    /**
     * 날짜별 신청 가능한 특강 목록
     */
    public List<Lecture> availableLecture(long userId){
        return lectureService.availableList(userId);
    }


    /**
     * 신청 완료 특강 목록
     */
    public List<Enrollment> enrolledLecture(long userId) {
        return lectureService.enrolledList(userId);
    }


}
