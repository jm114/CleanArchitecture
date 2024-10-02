package com.spring.clean.lecture.application.usecase;

import com.spring.clean.lecture.application.command.LectureCommand;
import com.spring.clean.lecture.application.service.LectureService;
import com.spring.clean.lecture.interfaces.dto.LectureRequest;
import com.spring.clean.lecture.interfaces.dto.LectureStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LectureFacade {

    private final LectureService lectureService;

    /**
     * 특강 신청
     * @param command
     */
    public void applyLecture(LectureCommand command) {
        //lectureService.isApplied(command.getLectureId());
    }

    /**
     *
     * @param command
     * @return
     */
    public void availableLectures (LectureCommand command) {

    }

    /**
     * 신청 완료 특강 목록
     */
    public void appliedLecture(long userId) {
        //return lectureService.appliedList(userId);
    }


}
