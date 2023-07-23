package io.winty.struct.meetingservice.dto;

import io.winty.struct.meetingservice.Meeting;

public record MeetingDTO(Long id, String name, String snowflake, String state){
    public MeetingDTO( Meeting meet){
        this(meet.getId(), meet.getName(), meet.getSnowflake(), meet.getState());
    }
}