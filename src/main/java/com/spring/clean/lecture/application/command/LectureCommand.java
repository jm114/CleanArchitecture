package com.spring.clean.lecture.application.command;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LectureCommand {
    private long userId;
    private long lectureId;
    private LocalDateTime applyTime;
}
