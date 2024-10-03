package com.spring.clean.lecture.interfaces.controller;


import com.spring.clean.lecture.application.command.LectureCommand;
import com.spring.clean.lecture.application.usecase.LectureFacade;
import com.spring.clean.lecture.interfaces.dto.LectureRequest;
import com.spring.clean.lecture.interfaces.dto.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
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
        return null;
    }
    
    /**
     * 특강 선택 API
     * - 신청 가능한 목록을 조회한다.
     */
    @GetMapping("/available")
    public ResponseEntity<LectureResponse> availableLectures(LectureRequest request){
        return null;
    }

    /**
     * 특강 신청 완료 목록 조회 API
     * - userId별로 신청 완료된 목록 조회
     * - 특강id, 이름, 강연자 정보
     */
    @GetMapping("/applied")
    public ResponseEntity<LectureResponse> appliedLectures(LectureRequest request){
        return null;
    }


}
