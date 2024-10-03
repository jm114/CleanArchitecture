package com.spring.clean.lecture.application.service;

import com.spring.clean.lecture.application.command.LectureCommand;
import com.spring.clean.lecture.domain.repository.LectureRepository;
import com.spring.clean.lecture.interfaces.dto.LectureRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {

    public void registApplication(LectureCommand command) {}

    public boolean isApplied(long lectureId) {
        return false;
    }

    public LectureRequest availableList() {
        return null;
    }

    public LectureRequest appliedList(long userId) {
        return null;
    }


}

