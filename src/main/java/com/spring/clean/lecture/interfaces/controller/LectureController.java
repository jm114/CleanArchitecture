package com.spring.clean.lecture.interfaces.controller;


import com.spring.clean.lecture.domain.Enrollment;
import com.spring.clean.lecture.domain.Lecture;
import com.spring.clean.lecture.application.usecase.LectureFacade;
import com.spring.clean.lecture.exception.EnrollmentException;
import com.spring.clean.lecture.exception.NoAvailableLecturesException;
import com.spring.clean.lecture.interfaces.dto.LectureRequest;
import com.spring.clean.lecture.interfaces.dto.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureFacade lecture;

    /**
     * 특강 신청 API
     * - userId별 선착순으로 신청
     * - 선착순 30명
     * - 신청하기 전에 목록 조회해야함
     * - (step3)30명 초과된 이후의 신청은 실패
     * - (step4)동일한 강의에 한번만 신청 가능
     */
    @PostMapping("/apply")
    public ResponseEntity<LectureResponse> applyLecture(LectureRequest request){
        try {
            Enrollment result = lecture.applyLecture(Enrollment.toDomain(request));

            if (result == null) {
                throw new EnrollmentException("수강 신청을 실패하였습니다.");
            }

            LectureResponse response = LectureResponse.builder()
                    .userId(result.getUserId())
                    .lectureId(result.getLectureId())
                    .status("수강 신청을 성공하였습니다").build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (EnrollmentException e) {
            LectureResponse response = LectureResponse.builder()
                    .userId(request.getUserId())
                    .lectureId(request.getLectureId())
                    .status("수강 신청을 실패하였습니다.").build();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

    }

    /**
     * 특강 신청 여부 조회 API
     *
     */
    @GetMapping("/applyCheck")
    public ResponseEntity<String> applyCheck(LectureRequest request){
        String result = lecture.checkLecture(Enrollment.toDomain(request));

        if ("신청 가능".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            throw new EnrollmentException("수강 신청이 불가능합니다.");
        }
    }


    /**
     * 특강 선택 API
     * - 신청 가능한 목록을 조회한다.
     */
    @GetMapping("/lists")
    public ResponseEntity<List<Lecture>> lectures(LectureRequest request){
        List<Lecture> lectures = lecture.availableLecture(Enrollment.toDomain(request));

        if (lectures.isEmpty()) {
            throw new NoAvailableLecturesException("신청 가능한 특강이 없습니다");
        }

        return ResponseEntity.ok(lectures);
    }

    /**
     * 특강 신청 완료 목록 조회 API
     * - userId별로 신청 완료된 목록 조회
     * - 특강id, 이름, 강연자 정보
     */
    @GetMapping("/myLists")
    public ResponseEntity<List<Enrollment>> enrolledlectures(LectureRequest request){
        List<Enrollment> lectures =  lecture.enrolledLecture(request.getUserId());
        if (lectures.isEmpty()) {
            throw new NoAvailableLecturesException("신청한 특강이 없습니다");
        }

        return ResponseEntity.ok(lectures);
    }


}
