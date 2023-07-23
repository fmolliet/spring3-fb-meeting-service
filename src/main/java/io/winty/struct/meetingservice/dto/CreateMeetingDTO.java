package io.winty.struct.meetingservice.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateMeetingDTO ( @NotBlank String name, @NotBlank String snowflake, @NotBlank String state ){}
